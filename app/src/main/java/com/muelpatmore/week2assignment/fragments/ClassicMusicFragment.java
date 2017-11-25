package com.muelpatmore.week2assignment.fragments;


import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muelpatmore.week2assignment.MusicListAdapter;
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
public class ClassicMusicFragment extends Fragment {

    public static final String TAG = "ClassicMusicFragment";


    private RequestInterface requestInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<SongModel> songList;


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
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        getClassicMusicList();

        Log.i(TAG, "Creating LayoutManager");
        layoutManager = new LinearLayoutManager(getActivity());

        if(savedInstanceState != null) {
            //ToDo restore instance on rotation/resumption
        }

        //initRecyclerLayout(songList);

    }

    public void initRecyclerLayout(List<SongModel> list) {
        Log.i(TAG, "initRecylerLayout");
        songList = new ArrayList<>(list);
        //get stored scroll position if already entered. (inspure by android dev esxample)
        int scrollPosition = 0;
        if(recyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
        // set layout type
//
//        if (songList == null) {
//            //ToDo replace with code to retrieve data from db or API
//            Log.e(TAG, "No data to display in RecyclerLayout");
//            return;
//        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAdapter = new MusicListAdapter(songList, scrollPosition, getContext());
        recyclerView.setAdapter(recyclerViewAdapter);

    }


    public void getClassicMusicList() {
        requestInterface = ServerConnection.getServerConnection();
        requestInterface.getClassicList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<MusicResultsModel>() {
                    @Override
                    public void accept(MusicResultsModel musicResultsModel) throws Exception {
                        Log.i(TAG, "API data recieved");
                        songList = new ArrayList<>(musicResultsModel.getResults());
                        initRecyclerLayout(musicResultsModel.getResults());
                        for (SongModel s : songList) {
                            Log.i(TAG, s.getTrackName());
                        }
                        Log.i(TAG, "List of "+songList.size()+"s read from API.");
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
    }
}
