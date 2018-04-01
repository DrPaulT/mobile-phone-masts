package uk.co.drdv.bink.mobilephonemasts.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import uk.co.drdv.bink.mobilephonemasts.R;
import uk.co.drdv.bink.mobilephonemasts.database.Mast;
import uk.co.drdv.bink.mobilephonemasts.database.initialisation.DbHelperIntentService;

public class AddMastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mast);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_mast, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_done) {
            Mast mast = new Mast(
                    ((EditText) findViewById(R.id.property_name)).getText().toString(),
                    ((EditText) findViewById(R.id.property_address1)).getText().toString(),
                    ((EditText) findViewById(R.id.property_address2)).getText().toString(),
                    ((EditText) findViewById(R.id.property_address3)).getText().toString(),
                    ((EditText) findViewById(R.id.property_address4)).getText().toString(),
                    ((EditText) findViewById(R.id.unit_name)).getText().toString(),
                    ((EditText) findViewById(R.id.tenant_name)).getText().toString(),
                    ((EditText) findViewById(R.id.lease_start_date)).getText().toString(),
                    ((EditText) findViewById(R.id.lease_end_date)).getText().toString(),
                    Integer.parseInt(((EditText) findViewById(R.id.lease_years)).getText().toString()),
                    ((EditText) findViewById(R.id.current_rent)).getText().toString()
            );
            DbHelperIntentService.addMast(getApplicationContext(), mast);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
