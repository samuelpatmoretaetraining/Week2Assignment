package com.muelpatmore.week2assignment.ui.tracklist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.muelpatmore.week2assignment.MusicListAdapter;
import com.muelpatmore.week2assignment.MyMusicApp;
import com.muelpatmore.week2assignment.R;
import com.muelpatmore.week2assignment.data.AppDataManager;
import com.muelpatmore.week2assignment.data.network.services.RequestInterface;
import com.muelpatmore.week2assignment.data.network.models.SongModel;
import com.muelpatmore.week2assignment.ui.utils.rx.AppSchedulerProvider;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrackListView extends Fragment implements SwipeRefreshLayout.OnRefreshListener, TrackListMvpView {

    public static final String TAG = TrackListView.class.getSimpleName();

    private static final String SCROLL_POSITION = "scroll position";


    private TrackListPresenter mTrackListPresenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RequestInterface mRequestInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerViewAdapter;
    private LinearLayoutManager mLayoutManager;

    private ArrayList<SongModel> mSongList;


    public TrackListView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classic_music, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTrackListPresenter = new TrackListPresenter(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        mTrackListPresenter.onAttach(this);
        mTrackListPresenter.onViewPrepared(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        // restore instance state on reload
        if(savedInstanceState == null) {
            onRefresh();
        } else {
            mLayoutManager.scrollToPositionWithOffset(savedInstanceState.getInt(SCROLL_POSITION, 0), 0);
        }

    }

    public void initRecyclerLayout() {
        Log.i(TAG, "initRecylerLayout");
        //get stored scroll position if already entered. (inspure by android dev esxample)
        int scrollPosition = 0;
        if(mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
        //set layout type

        if (mSongList == null) {
            //ToDo replace with code to retrieve data from db or API

            Log.e(TAG, "No data to display in RecyclerLayout");
            return;
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewAdapter = new MusicListAdapter(mSongList, scrollPosition, getContext());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.getLayoutManager().scrollToPosition(scrollPosition);


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        int scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        savedInstanceState.putInt(SCROLL_POSITION, scrollPosition);
        Log.i(TAG, "Scroll position "+scrollPosition+" saved in instance state.");
    }

    @Override
    public void onRefresh() {
        Log.i(TAG, "Refreshing content");
        Toast.makeText(MyMusicApp.getContext(), "Refreshing content", Toast.LENGTH_SHORT).show();
        mTrackListPresenter.getTrackList();
    }

    public void setTrackList(ArrayList<SongModel> mSongList) {
        this.mSongList = mSongList;
    }

    @Override
    public void onFetchDataSuccess() {
        Log.i(TAG, "New data recieved");
        initRecyclerLayout();
        if(mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {

    }
}
