package uk.co.drdv.bink.mobilephonemasts.database.initialisation;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import uk.co.drdv.bink.mobilephonemasts.MpmApplication;
import uk.co.drdv.bink.mobilephonemasts.database.Mast;

public class DbHelperIntentService extends IntentService {

    private static final String EXTRA_LOAD_CSV =
            "uk.co.drdv.bink.mobilephonemasts.database.initialisation."
                    + "DbHelperIntentService.LOAD_CSV";
    private static final String EXTRA_ADD_MAST =
            "uk.co.drdv.bink.mobilephonemasts.database.initialisation."
                    + "DbHelperIntentService.ADD_MAST";
    private static final String EXTRA_SERIALIZABLE =
            "uk.co.drdv.bink.mobilephonemasts.database.initialisation."
                    + "DbHelperIntentService.SERIALIZABLE";
    private static final String DATA_FILE = "MobilePhoneMasts.csv";
    private static final int PROPERTY_NAME = 0;
    private static final int PROPERTY_ADDRESS1 = 1;
    private static final int PROPERTY_ADDRESS2 = 2;
    private static final int PROPERTY_ADDRESS3 = 3;
    private static final int PROPERTY_ADDRESS4 = 4;
    private static final int UNIT_NAME = 5;
    private static final int TENANT_NAME = 6;
    private static final int LEASE_START_DATE = 7;
    private static final int LEASE_END_DATE = 8;
    private static final int LEASE_YEARS = 9;
    private static final int CURRENT_RENT = 10;

    private ArrayList<String> lines = new ArrayList<>();
    private ArrayList<Mast> masts = new ArrayList<>();
    private DateFormat inputFormat = new SimpleDateFormat("d MMM yyyy", Locale.getDefault());
    private DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public DbHelperIntentService() {
        super("DbHelperIntentService");
    }

    public static void loadCsv(Context context) {
        Intent intent = new Intent(context, DbHelperIntentService.class);
        intent.putExtra(EXTRA_LOAD_CSV, true);
        context.startService(intent);
    }

    public static void addMast(Context context, Mast mast) {
        Intent intent = new Intent(context, DbHelperIntentService.class);
        intent.putExtra(EXTRA_ADD_MAST, true);
        intent.putExtra(EXTRA_SERIALIZABLE, mast);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent.getBooleanExtra(EXTRA_LOAD_CSV, false)) {
            loadCsvFromAssets();
        } else if (intent.getBooleanExtra(EXTRA_ADD_MAST, false)) {
            try {
                Mast mast = (Mast) intent.getSerializableExtra(EXTRA_SERIALIZABLE);
                mast.setLeaseStartDate(formatDate(mast.getLeaseStartDate()));
                mast.setLeaseEndDate(formatDate(mast.getLeaseEndDate()));
                addMastToDatabase((Mast) intent.getSerializableExtra(EXTRA_SERIALIZABLE));
            } catch (ParseException parseException) {
                // Discard this row.
                parseException.printStackTrace();
            }
        }
    }

    private void loadCsvFromAssets() {
        if (MpmApplication.mastDatabase.mastDao().countRows() == 0) {
            try {
                loadDataFromAssets();
                parseData();
                addMastsToDatabase();
            } catch (Exception exception) {
                // Ignore any lines that fail.
                exception.printStackTrace();
            }
        }
    }

    private void loadDataFromAssets() throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(
                    new InputStreamReader(getResources().getAssets().open(DATA_FILE)));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.trim());
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    private void parseData() {
        StringBuilder builder = new StringBuilder();
        for (String line : lines) {
            if (!line.startsWith("Property Name")) {
                builder.delete(0, builder.length());
                String[] invertedCommaBits = line.split("\"");
                for (int i = 0; i < invertedCommaBits.length; i++) {
                    if ((i % 2) == 0) { // Outside quoted string, replace commas with pipes.
                        invertedCommaBits[i] = invertedCommaBits[i].replace(",", "|");
                    }
                    builder.append(invertedCommaBits[i]);
                }
                String pipeSeparated = builder.toString();
                String bits[] = pipeSeparated.split("\\|");
                try {
                    masts.add(new Mast(
                            bits[PROPERTY_NAME],
                            bits[PROPERTY_ADDRESS1],
                            bits[PROPERTY_ADDRESS2],
                            bits[PROPERTY_ADDRESS3],
                            bits[PROPERTY_ADDRESS4],
                            bits[UNIT_NAME],
                            bits[TENANT_NAME],
                            formatDate(bits[LEASE_START_DATE]),
                            formatDate(bits[LEASE_END_DATE]),
                            Integer.parseInt(bits[LEASE_YEARS]),
                            bits[CURRENT_RENT]));
                } catch (java.text.ParseException parseException) {
                    // Discard line on error.
                    parseException.printStackTrace();
                }
            }
        }
    }

    private String formatDate(String dateString) throws ParseException {
        Date date = inputFormat.parse(dateString);
        return outputFormat.format(date);
    }

    private void addMastsToDatabase() {
        for (Mast mast : masts) {
            addMastToDatabase(mast);
        }
    }

    private void addMastToDatabase(Mast mast) {
        MpmApplication.mastDatabase.mastDao().insert(mast);
    }
}
