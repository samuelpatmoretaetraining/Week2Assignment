package com.muelpatmore.week2assignment.data.network.services;

import com.muelpatmore.week2assignment.data.constants.API_Constants;
import com.muelpatmore.week2assignment.data.network.models.MusicResultsModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Samuel on 24/11/2017.
 *
 * List of Retrofit compliant HTTP requests for instantiation by ConnectionService.
 */
public interface RequestInterface {

    @GET(API_Constants.CLASSIC_QUERY)
    Observable<MusicResultsModel> getClassicList();

    @GET(API_Constants.ROCK_QUERY)
    Observable<MusicResultsModel> getRockList();

    @GET(API_Constants.POP_QUERY)
    Observable<MusicResultsModel> getPopList();

}
