package com.muelpatmore.week2assignment.data;

import com.muelpatmore.week2assignment.data.network.APIHelper;
import com.muelpatmore.week2assignment.data.network.AppAPIHelper;
import com.muelpatmore.week2assignment.data.network.models.MusicResultsModel;

import io.reactivex.Observable;

/**
 * Created by Sam on 25/11/2017.
 */

public class AppDataManager implements IDataManager {

    private APIHelper mAPIHelper;

    public AppDataManager() {
        this.mAPIHelper = new AppAPIHelper();
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
