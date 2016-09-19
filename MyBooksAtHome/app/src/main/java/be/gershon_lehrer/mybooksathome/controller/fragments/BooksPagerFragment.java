package be.gershon_lehrer.mybooksathome.controller.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import be.gershon_lehrer.mybooksathome.App;
import be.gershon_lehrer.mybooksathome.R;
import be.gershon_lehrer.mybooksathome.model.Book;

/**
 * Created by gershonlehrer on 11/07/16.
 */
public class BooksPagerFragment extends Fragment implements App.OnBookListChangedListener {
    private ViewPager mViewPager;
    private List<Book> mBookList;
    private static String mBookId;
private static final String BOOK_DETAIL_INTENT="be.gershon-lehrer.mybooksathome.book_detail_intent";
    private static final String POSITION="be.gershon-lehrer.mybooksathome.book_detail_position";
    private FragmentStatePagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_books_pager, container, false);
        mBookList= App.getInstance().getBookList();
        mViewPager=(ViewPager)view.findViewById(R.id.activity_books_pager_view_pager);
        mBookId=getArguments().getString(BOOK_DETAIL_INTENT);

        App.getInstance().addOnBookListChangedListener(this);

        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        adapter = new FragmentStatePagerAdapter(fragmentManager){
            /**
             * Return the number of views available.
             */
            @Override
            public int getCount() {
                return App.getInstance().getBookList().size();
            }

            /**
             * Return the Fragment associated with a specified position.
             *
             * @param position
             */
            @Override
            public Fragment getItem(int position) {
                BookDetailFragment bookDetailFragment = newBookDetailFragment(position);
                return bookDetailFragment;
            }
        };
        mViewPager.setAdapter(adapter);
        for (int i=0;i<mBookList.size();i++){
            if (mBookList.get(i).getId().equals(mBookId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
        adapter.notifyDataSetChanged();
        return view;
    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_books_pager);
////        int bookPositionInList=(int)getIntent().getSerializableExtra(BOOK_DETAIL_INTENT);
//
//        mBookList= App.getInstance().getBookList();
//
//        mViewPager=(ViewPager)findViewById(R.id.activity_books_pager_view_pager);
//        FragmentManager fragmentManager=getSupportFragmentManager();
//        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager){
//            @Override
//            public Fragment getItem(int position) {
//                //TODO: verander dit later evt na aanmaken getinstance
//                //TODO: docent laten zien
////                Bundle args=new Bundle();
////                args.putInt("POSITION",position);
////                BookDetailFragment bookDetailFragment=new BookDetailFragment();
////                bookDetailFragment.setArguments(args);
//                BookDetailFragment bookDetailFragment = newBookDetailFragment(mBookList.get(position).getId());
//                return bookDetailFragment;
//            }
//
//            @Override
//            public int getCount() {
//                return App.getInstance().getBookList().size();
//            }
//        });
//
//        for (int i=0;i<mBookList.size();i++){
//            if (mBookList.get(i).getId().equals(mBookId)){
//                mViewPager.setCurrentItem(i);
//                break;
//            }
//        }
//    }

    public static Intent newIntent(Context packageContext,String bookId){
        Intent intent=new Intent(packageContext,BooksPagerFragment.class);
        intent.putExtra(BOOK_DETAIL_INTENT, bookId);
        mBookId=bookId;

        return intent;
    }


    public static BookDetailFragment newBookDetailFragment(int position){
        Bundle args=new Bundle();
        args.putInt(POSITION,position);
        BookDetailFragment bookDetailFragment=new BookDetailFragment();
        bookDetailFragment.setArguments(args);
        Log.d(App.getTAG(),bookDetailFragment.toString()); //TODO:dit geeft null???
        return bookDetailFragment;
    }

    @Override
    public void onBookListChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBookItemRemoved(int position) {
        //adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.getInstance().removeOnBookListChangedListener(this);
    }

    public static BooksPagerFragment newBooksPagerActivity(String bookId){
        Bundle args=new Bundle();
        args.putString(BOOK_DETAIL_INTENT,bookId);
        BooksPagerFragment fragment=new BooksPagerFragment();
        fragment.setArguments(args);
        Log.d(App.getTAG(),fragment.toString()); //TODO:dit geeft null???
        return fragment;
    }


}
