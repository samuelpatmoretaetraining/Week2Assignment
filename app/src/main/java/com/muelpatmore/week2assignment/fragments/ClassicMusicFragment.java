package com.muelpatmore.week2assignment.fragments;


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
import com.muelpatmore.week2assignment.data.network.services.RequestInterface;
import com.muelpatmore.week2assignment.data.network.services.ServerConnection;
import com.muelpatmore.week2assignment.data.network.services.models.MusicResultsModel;
import com.muelpatmore.week2assignment.data.network.services.models.SongModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassicMusicFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    public static final String TAG = ClassicMusicFragment.class.getSimpleName();

    private static final String SCROLL_POSITION = "scroll position";


    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RequestInterface mRequestInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerViewAdapter;
    private LinearLayoutManager mLayoutManager;

    private ArrayList<SongModel> mSongList;


    public ClassicMusicFragment() {
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
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        getClassicMusicList();

        Log.i(TAG, "Creating LayoutManager");
        mLayoutManager = new LinearLayoutManager(getActivity());

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        // restore instance state on reload
        if(savedInstanceState != null) {
            // restore scroll position in RecyclerView
            int scrollPosition = savedInstanceState.getInt(SCROLL_POSITION, 0);
            if (mRecyclerView.getLayoutManager() != null) {
                //ToDo this is not working yet
                mRecyclerView.getLayoutManager().scrollToPosition(scrollPosition);
            }
        }

        //initRecyclerLayout(mSongList);

    }

    public void initRecyclerLayout(List<SongModel> list) {
        Log.i(TAG, "initRecylerLayout");
        mSongList = new ArrayList<>(list);
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

    public void getClassicMusicList() {
        mRequestInterface = ServerConnection.getServerConnection();
        mRequestInterface.getClassicList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<MusicResultsModel>() {
                    @Override
                    public void accept(MusicResultsModel musicResultsModel) throws Exception {
                        Log.i(TAG, "API data recieved");
                        mSongList = new ArrayList<>(musicResultsModel.getResults());
                        initRecyclerLayout(musicResultsModel.getResults());
                        Log.i(TAG, "List of "+ mSongList.size()+"s read from API.");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        int scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        savedInstanceState.putInt(SCROLL_POSITION, scrollPosition);
        Log.i(TAG, "Scroll positon saved in instance state.");
    }

    @Override
    public void onRefresh() {
        Log.i(TAG, "Refreshing content");
        Toast.makeText(MyMusicApp.getContext(), "Refreshing content", Toast.LENGTH_SHORT).show();
        getClassicMusicList();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
