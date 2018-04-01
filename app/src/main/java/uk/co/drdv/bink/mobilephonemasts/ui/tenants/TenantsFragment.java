package uk.co.drdv.bink.mobilephonemasts.ui.tenants;

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
import uk.co.drdv.bink.mobilephonemasts.database.MastRepository;
import uk.co.drdv.bink.mobilephonemasts.database.MastViewModel;
import uk.co.drdv.bink.mobilephonemasts.database.TenantCount;

public class TenantsFragment extends Fragment implements Observer<TenantCount[]> {

    private MastViewModel mastViewModel;
    private TenantsAdapter tenantsAdapter;

    public TenantsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tenantsAdapter = new TenantsAdapter();
        mastViewModel = ViewModelProviders.of(this).get(MastViewModel.class);
        mastViewModel.initialise(getContext(), new MastRepository());
        mastViewModel.getTenantCount().observe(this, this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tenants, container, false);
        ListView listView = view.findViewById(R.id.list_view);
        listView.setAdapter(tenantsAdapter);
        return view;
    }

    @Override
    public void onChanged(@Nullable TenantCount[] tenantCounts) {
        tenantsAdapter.setTenantCounts(tenantCounts);
    }
}
