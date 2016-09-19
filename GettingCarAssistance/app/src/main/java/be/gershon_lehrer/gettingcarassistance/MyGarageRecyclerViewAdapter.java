package be.gershon_lehrer.gettingcarassistance;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import be.gershon_lehrer.gettingcarassistance.model.Garage;

import java.util.ArrayList;
import java.util.List;


public class MyGarageRecyclerViewAdapter extends RecyclerView.Adapter<MyGarageRecyclerViewAdapter.ViewHolder> {

    private final List<Garage> mGarageList=new ArrayList<>();



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_garage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mGarageList.get(position);
        holder.mIdGarageTextView.setText("id");
        holder.mGarageNameTextview.setText(holder.mItem.getName());
        holder.mGarageAddressTextView.setText(holder.mItem.getStreetAddress()+"\n"+
                holder.mItem.getPostCode()+"\n"+
                holder.mItem.getCountry());




        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mGarageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdGarageTextView;
        public final TextView mGarageNameTextview;
        public final TextView mGarageAddressTextView;

        public Garage mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdGarageTextView = (TextView) view.findViewById(R.id.id_textview);
            mGarageNameTextview = (TextView) view.findViewById(R.id.garage_name_textview);
            mGarageAddressTextView= (TextView)view.findViewById(R.id.garage_address_textview);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mGarageNameTextview.getText() + "'";
        }
    }
}
