package com.muelpatmore.week2assignment.data.network.services;

import com.muelpatmore.week2assignment.data.constants.API_Constants;

import java.util.List;

/**
 * Created by Samuel on 24/11/2017.
 */

public interface RequestInterface {

    @GET(API_Constants.)
        // ensure Observable if from reactivex library (unless data is >1,000)
        // List as JSON schema starts with an array ( '[' )
    Observable<> getCakesList();

}
