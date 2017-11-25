package com.muelpatmore.week2assignment.ui.tracklist;

import com.muelpatmore.week2assignment.ui.base.MvpPresenter;

/**
 * Created by Sam on 25/11/2017.
 */

public interface TrackListMvpPresenter<v extends TrackListMvpView> extends MvpPresenter{
    public void onViewPrepared();

}
