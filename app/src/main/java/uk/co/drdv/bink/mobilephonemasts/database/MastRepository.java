package uk.co.drdv.bink.mobilephonemasts.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import uk.co.drdv.bink.mobilephonemasts.MpmApplication;
import uk.co.drdv.bink.mobilephonemasts.database.initialisation.InitialiseIntentService;

public class MastRepository {

    public void loadMasts(Context context) {
        InitialiseIntentService.initialiseData(context);
    }

    public LiveData<Mast[]> getBottom5() {
        return MpmApplication.mastDatabase.mastDao().selectBottom5();
    }

    public LiveData<Mast[]> getTop5() {
        return MpmApplication.mastDatabase.mastDao().selectTop5();
    }

    public LiveData<TenantCount[]> getTenantCount() {
        return MpmApplication.mastDatabase.mastDao().selectTenantCount();
    }

    public LiveData<Mast[]> getLeaseDates() {
        return MpmApplication.mastDatabase.mastDao().selectLeaseDates();
    }
}
