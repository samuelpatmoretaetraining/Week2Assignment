package com.muelpatmore.week2assignment.ui.tracklist;

import android.util.Log;
import android.widget.Toast;

import com.muelpatmore.week2assignment.MyMusicApp;
import com.muelpatmore.week2assignment.data.AppDataManager;
import com.muelpatmore.week2assignment.data.IDataManager;
import com.muelpatmore.week2assignment.data.network.models.MusicResultsModel;
import com.muelpatmore.week2assignment.data.network.models.SongModel;
import com.muelpatmore.week2assignment.messages.ClassicTrackListMessage;
import com.muelpatmore.week2assignment.ui.base.BasePresenter;
import com.muelpatmore.week2assignment.ui.base.MvpPresenter;
import com.muelpatmore.week2assignment.ui.base.MvpView;
import com.muelpatmore.week2assignment.ui.utils.rx.SchedulerProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Sam on 25/11/2017.
 */

public class TrackListPresenter extends BasePresenter implements MvpPresenter {

    private final static String TAG = TrackListPresenter.class.getSimpleName();

    public static final int GENRE = 0;

    private TrackListMvpView mTrackListMvpView;

    public TrackListPresenter(IDataManager dataManager,
                              SchedulerProvider schedulerProvider,
                              CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }



    public void getTrackList() {
        ArrayList<SongModel> songList = getDataManager().getTrackList(AppDataManager.CLASSIC);
        mTrackListMvpView.setTrackList(songList);
        mTrackListMvpView.onFetchDataSuccess();
        Toast.makeText(MyMusicApp.getContext(), "Song list passed to view: " + songList.size() + " songs.", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Song list passed to view: " + songList.size() + " songs.");
    }

    public void onViewPrepared(TrackListMvpView mTrackListMvpView) {
        this.mTrackListMvpView = mTrackListMvpView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ClassicTrackListMessage event) {
        mTrackListMvpView.setTrackList(event.trackList);
        mTrackListMvpView.onFetchDataSuccess();
        Toast.makeText(MyMusicApp.getContext(), "EventBus message recieved: " + event.trackList.size() + " songs.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(MvpView mvpView) {
        super.onAttach(mvpView);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean isViewAttached() {
        return super.isViewAttached();
    }

    @Override
    public MvpView getMvpView() {
        return super.getMvpView();
    }

    @Override
    public void checkViewAttached() {
        super.checkViewAttached();
    }

    @Override
    public IDataManager getDataManager() {
        return super.getDataManager();
    }

    @Override
    public SchedulerProvider getSchedulerProvider() {
        return super.getSchedulerProvider();
    }

    @Override
    public CompositeDisposable getCompositeDisposable() {
        return super.getCompositeDisposable();
    }


}