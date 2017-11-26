package com.muelpatmore.week2assignment.data.network;

import com.muelpatmore.week2assignment.data.constants.API_Constants;
import com.muelpatmore.week2assignment.data.network.models.MusicResultsModel;

import io.reactivex.Observable;

/**
 * Created by Sam on 25/11/2017.
 *
 * Interface to API web-service request functions provided by classes that implement this interface.
 */
public interface APIHelper {
    Observable<MusicResultsModel> getClassicList();
    Observable<MusicResultsModel> getRockList();
    Observable<MusicResultsModel> getPopList();
}
