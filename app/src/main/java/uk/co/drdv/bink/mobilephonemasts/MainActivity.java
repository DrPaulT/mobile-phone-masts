package uk.co.drdv.bink.mobilephonemasts;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import uk.co.drdv.bink.mobilephonemasts.database.Mast;
import uk.co.drdv.bink.mobilephonemasts.database.MastRepository;
import uk.co.drdv.bink.mobilephonemasts.database.MastViewModel;

public class MainActivity extends AppCompatActivity
        implements Observer<Mast[]> {

    private MastViewModel mastViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mastViewModel = ViewModelProviders.of(this).get(MastViewModel.class);
        mastViewModel.initialise(getApplicationContext(), new MastRepository());
        mastViewModel.getMasts().observe(this, this);
    }

    @Override
    public void onChanged(@Nullable Mast[] masts) {
        // TODO    Update list adapter.
        Log.i("CHANGED_MASTS", "Masts = " + masts);
    }
}
