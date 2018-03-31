package uk.co.drdv.bink.mobilephonemasts.ui.rents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import uk.co.drdv.bink.mobilephonemasts.R;
import uk.co.drdv.bink.mobilephonemasts.database.Mast;

public class RentsAdapter extends BaseAdapter {

    private Mast[] masts;
    private LayoutInflater layoutInflater;

    @Override
    public int getCount() {
        if (masts != null) {
            return masts.length;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (masts != null && position < masts.length) {
            return masts[position];
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
                view = layoutInflater.inflate(R.layout.list_item_rents, parent, false);
            }
        }
        if (view != null) {
            ((TextView) view.findViewById(R.id.name)).setText(masts[position].getPropertyName());
            ((TextView) view.findViewById(R.id.address)).setText(
                    parent.getContext().getString(R.string.address,
                            masts[position].getPropertyAddress1(),
                            masts[position].getPropertyAddress2(),
                            masts[position].getPropertyAddress3(),
                            masts[position].getPropertyAddress4()));
            ((TextView) view.findViewById(R.id.rent)).setText(
                    parent.getContext().getString(R.string.rent,
                            masts[position].getCurrentRent()));
            return view;
        }
        return null;
    }

    public void setMasts(Mast[] masts) {
        this.masts = masts;
        notifyDataSetChanged();
    }
}
