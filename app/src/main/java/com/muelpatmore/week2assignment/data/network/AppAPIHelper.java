package com.muelpatmore.week2assignment.data.network;

import android.util.Log;

import com.muelpatmore.week2assignment.MyMusicApp;
import com.muelpatmore.week2assignment.data.network.models.MusicResultsModel;
import com.muelpatmore.week2assignment.data.network.services.ConnectionService;
import com.muelpatmore.week2assignment.data.network.services.RequestInterface;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sam on 25/11/2017.
 *
 * Single resource for all API requests accessible through the APIHelper interface.
 */
public class AppAPIHelper implements APIHelper {

    private static final String TAG = APIHelper.class.getSimpleName();
    private RequestInterface mRequestInterface;

    /**
     * Get BASE_URL connection to API server.
     */
    public AppAPIHelper() {
        this.mRequestInterface = ConnectionService.BackendService();
    }

    @Override
    public Observable<MusicResultsModel> getClassicList() {
        return mRequestInterface.getClassicList();
    }

    @Override
    public Observable<MusicResultsModel> getRockList() {
        return mRequestInterface.getRockList();
    }

    @Override
    public Observable<MusicResultsModel> getPopList() {
        return mRequestInterface.getPopList();
    }
}
