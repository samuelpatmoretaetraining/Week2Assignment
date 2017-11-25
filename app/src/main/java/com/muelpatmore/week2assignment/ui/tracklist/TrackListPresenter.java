package com.muelpatmore.week2assignment.ui.tracklist;

import android.util.Log;

import com.muelpatmore.week2assignment.data.IDataManager;
import com.muelpatmore.week2assignment.data.network.models.MusicResultsModel;
import com.muelpatmore.week2assignment.data.network.models.SongModel;
import com.muelpatmore.week2assignment.ui.base.BasePresenter;
import com.muelpatmore.week2assignment.ui.base.MvpPresenter;
import com.muelpatmore.week2assignment.ui.base.MvpView;
import com.muelpatmore.week2assignment.ui.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Sam on 25/11/2017.
 */

public class TrackListPresenter extends BasePresenter implements MvpPresenter {

    private final static String TAG = TrackListPresenter.class.getSimpleName();

    private TrackListMvpView mTrackListMvpView;

    public TrackListPresenter(IDataManager dataManager,
                              SchedulerProvider schedulerProvider,
                              CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);

    }

    public void getTrackList() {
        //ToDo check network status
        getCompositeDisposable().add(getDataManager()
            .getClassicList()
            .subscribeOn(getSchedulerProvider().io())
            .observeOn(getSchedulerProvider().ui())
            .subscribe(new Consumer<MusicResultsModel>() {
                @Override
                public void accept(MusicResultsModel musicResultsModel) throws Exception {
                    Log.i(TAG, "Data lookup success");
                    ArrayList<SongModel> songList = new ArrayList<>(musicResultsModel.getResults());
                    mTrackListMvpView.setTrackList(songList);
                    mTrackListMvpView.onFetchDataSuccess();
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    throwable.printStackTrace();
                }
            }));
    }

    public void onViewPrepared(TrackListMvpView mTrackListMvpView) {

        this.mTrackListMvpView = mTrackListMvpView;
    }

    @Override
    public void onAttach(MvpView mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
