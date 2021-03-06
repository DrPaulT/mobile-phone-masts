package uk.co.drdv.bink.mobilephonemasts.ui.rents;

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
import android.widget.TextView;

import java.util.Locale;

import uk.co.drdv.bink.mobilephonemasts.R;
import uk.co.drdv.bink.mobilephonemasts.database.Mast;
import uk.co.drdv.bink.mobilephonemasts.database.MastRepository;
import uk.co.drdv.bink.mobilephonemasts.database.MastViewModel;

public class RentsFragment extends Fragment
        implements Observer<Mast[]>, View.OnClickListener {

    private MastViewModel mastViewModel;
    private RentsAdapter rentsAdapter;
    private boolean sortAscending = true;
    private TextView total;

    public RentsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rentsAdapter = new RentsAdapter();
        mastViewModel = ViewModelProviders.of(this).get(MastViewModel.class);
        mastViewModel.initialise(getContext(), new MastRepository());
        mastViewModel.getMastsAscending().observe(this, this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rents, container, false);
        ListView listView = view.findViewById(R.id.list_view);
        listView.setAdapter(rentsAdapter);
        view.findViewById(R.id.reverse_sort).setOnClickListener(this);
        total = view.findViewById(R.id.total);
        return view;
    }

    @Override
    public void onChanged(@Nullable Mast[] masts) {
        if (masts != null) {
            rentsAdapter.setMasts(masts);
            int totalPennies = 0;
            for (Mast mast : masts) {
                totalPennies += (Double.parseDouble(mast.getCurrentRent()) * 100);
            }
            total.setText(
                    getString(R.string.rent, String.format(Locale.getDefault(), "%.2f", totalPennies / 100.0)));
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.reverse_sort) {
            if (sortAscending) {
                mastViewModel.getMastsAscending().removeObserver(this);
                mastViewModel.getMastsDescending().observe(this, this);
            } else {
                mastViewModel.getMastsDescending().removeObserver(this);
                mastViewModel.getMastsAscending().observe(this, this);
            }
            sortAscending = !sortAscending;
        }
    }
}
