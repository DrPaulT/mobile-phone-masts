package uk.co.drdv.bink.mobilephonemasts.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

public class MastViewModel extends ViewModel {

    private MastRepository mastRepository;

    public void initialise(Context context, MastRepository mastRepository) {
        this.mastRepository = mastRepository;
        mastRepository.loadMasts(context);
    }

    public LiveData<Mast[]> getMastsAscending() {
        return mastRepository.getBottom5();
    }

    public LiveData<Mast[]> getMastsDescending() {
        return mastRepository.getTop5();
    }
}
