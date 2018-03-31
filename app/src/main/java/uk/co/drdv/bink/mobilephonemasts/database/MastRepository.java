package uk.co.drdv.bink.mobilephonemasts.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import uk.co.drdv.bink.mobilephonemasts.MpmApplication;
import uk.co.drdv.bink.mobilephonemasts.initialisation.InitialiseIntentService;

public class MastRepository {

    private boolean loaded = false;

    public LiveData<Mast[]> loadMasts(Context context) {
        if (!loaded) { // TODO    I think we might not need this check.
            InitialiseIntentService.initialiseData(context);
            loaded = true;
        }
        return MpmApplication.mastDatabase.mastDao().selectAll();
    }
}
