package com.muelpatmore.week2assignment.data;

import com.muelpatmore.week2assignment.data.network.APIHelper;
import com.muelpatmore.week2assignment.data.network.models.SongModel;
import com.muelpatmore.week2assignment.data.realm.IRealmHelper;

import java.util.ArrayList;

/**
 * Created by Sam on 25/11/2017.
 */

public interface IDataManager extends APIHelper, IRealmHelper {
    public ArrayList<SongModel> getTrackList(String genre);
}
