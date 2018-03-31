package uk.co.drdv.bink.mobilephonemasts.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

public class MastViewModel extends ViewModel {

    private LiveData<Mast[]> masts;

    public void initialise(Context context, MastRepository mastRepository) {
        if (masts == null) {
            masts = mastRepository.loadMasts(context);
        }
    }

    public LiveData<Mast[]> getMasts() {
        return masts;
    }
}
