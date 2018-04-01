package uk.co.drdv.bink.mobilephonemasts.ui.tenants;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import uk.co.drdv.bink.mobilephonemasts.R;
import uk.co.drdv.bink.mobilephonemasts.database.TenantCount;

public class TenantsAdapter extends BaseAdapter {

    private TenantCount[] tenantCounts;
    private LayoutInflater layoutInflater;

    @Override
    public int getCount() {
        if (tenantCounts != null) {
            return tenantCounts.length;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (tenantCounts != null && position < tenantCounts.length) {
            return tenantCounts[position];
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
                view = layoutInflater.inflate(R.layout.list_item_tenants, parent, false);
            }
        }
        if (view != null) {
            ((TextView) view.findViewById(R.id.tenant))
                    .setText(tenantCounts[position].getTenantName());
            ((TextView) view.findViewById(R.id.count))
                    .setText(String.valueOf(tenantCounts[position].getNum()));
            return view;
        }
        return null;
    }

    public void setTenantCounts(TenantCount[] tenantCounts) {
        this.tenantCounts = tenantCounts;
        notifyDataSetChanged();
    }
}
