package uk.co.drdv.bink.mobilephonemasts.ui.dates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import uk.co.drdv.bink.mobilephonemasts.R;
import uk.co.drdv.bink.mobilephonemasts.database.Mast;

public class DatesAdapter extends BaseAdapter {

    private Mast[] masts;
    private LayoutInflater layoutInflater;
    private DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private DateFormat outputFormat = new SimpleDateFormat("d MMMM yyyy", Locale.getDefault());

    @Override
    public int getCount() {
        if (masts != null) {
            return masts.length;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (masts != null && position < masts.length) {
            return masts[position];
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            if (layoutInflater == null) {
                layoutInflater = parent.getContext().getSystemService(LayoutInflater.class);
            }
            if (layoutInflater != null) {
                view = layoutInflater.inflate(R.layout.list_item_dates, parent, false);
            }
        }
        if (view != null) {
            try {
                ((TextView) view.findViewById(R.id.name))
                        .setText(masts[position].getPropertyName());
                ((TextView) view.findViewById(R.id.lease_dates))
                        .setText(parent.getContext().getString(R.string.date_range,
                                formatDate(masts[position].getLeaseStartDate()),
                                formatDate(masts[position].getLeaseEndDate())));
                return view;
            } catch (ParseException parseException) {
                // Ignore this item.
                parseException.printStackTrace();
            }
        }
        return null;
    }

    public void setMasts(Mast[] masts) {
        this.masts = masts;
        notifyDataSetChanged();
    }

    private String formatDate(String dateString) throws ParseException {
        Date date = inputFormat.parse(dateString);
        return outputFormat.format(date);
    }
}
