package com.muelpatmore.week2assignment.ui.tracklist;

import com.muelpatmore.week2assignment.data.network.models.SongModel;
import com.muelpatmore.week2assignment.ui.base.MvpView;

import java.util.ArrayList;

/**
 * Created by Sam on 25/11/2017.
 */

public interface TrackListMvpView extends MvpView {
    public void onFetchDataSuccess();
    public void setTrackList(ArrayList<SongModel> mSongList);
}
