package be.gershon_lehrer.gettingcarassistance;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import be.gershon_lehrer.gettingcarassistance.controller.DataLinkLayer;
import be.gershon_lehrer.gettingcarassistance.dummy.DummyContent.DummyItem;
import be.gershon_lehrer.gettingcarassistance.model.Garage;


public class GarageFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private RecyclerView mRecyclerView;
    private MyGarageRecyclerViewAdapter mAdapter;
    private DataLinkLayer mDataLinkLayer=new DataLinkLayer();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GarageFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static GarageFragment newInstance(int columnCount) {
        GarageFragment fragment = new GarageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_garage_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_items_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore(int current_page) {
                mDataLinkLayer.getGarageObservable();
            }
        });

        return view;
    }
    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new MyGarageRecyclerViewAdapter();
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnGarageListReceivedListener {
        // TODO: Update argument type and name
        void onGarageListReceived(Garage item);
    }
}
