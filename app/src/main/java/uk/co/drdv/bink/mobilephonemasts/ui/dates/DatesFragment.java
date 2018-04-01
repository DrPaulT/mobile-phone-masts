package uk.co.drdv.bink.mobilephonemasts.ui.dates;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import uk.co.drdv.bink.mobilephonemasts.R;
import uk.co.drdv.bink.mobilephonemasts.database.Mast;
import uk.co.drdv.bink.mobilephonemasts.database.MastRepository;
import uk.co.drdv.bink.mobilephonemasts.database.MastViewModel;

public class DatesFragment extends Fragment implements Observer<Mast[]> {

    private MastViewModel mastViewModel;
    private DatesAdapter datesAdapter;

    public DatesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datesAdapter = new DatesAdapter();
        mastViewModel = ViewModelProviders.of(this).get(MastViewModel.class);
        mastViewModel.initialise(getContext(), new MastRepository());
        mastViewModel.getLeaseDates().observe(this, this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dates, container, false);
        ListView listView = view.findViewById(R.id.list_view);
        listView.setAdapter(datesAdapter);
        return view;
    }

    @Override
    public void onChanged(@Nullable Mast[] masts) {
        datesAdapter.setMasts(masts);
    }
}
