package com.muelpatmore.week2assignment.data.network;

import com.muelpatmore.week2assignment.MyMusicApp;
import com.muelpatmore.week2assignment.data.network.models.MusicResultsModel;
import com.muelpatmore.week2assignment.data.network.services.ConnectionService;
import com.muelpatmore.week2assignment.data.network.services.RequestInterface;

import io.reactivex.Observable;

/**
 * Created by Sam on 25/11/2017.
 */

public class AppAPIHelper implements APIHelper {

    private RequestInterface mRequestInterface;

    public AppAPIHelper() {
        this.mRequestInterface = ConnectionService.getConnection();
    }

    @Override
    public Observable<MusicResultsModel> getClassicList() {
        return null;
    }

    @Override
    public Observable<MusicResultsModel> getRockList() {
        return null;
    }

    @Override
    public Observable<MusicResultsModel> getPopList() {
        return null;
    }
}
