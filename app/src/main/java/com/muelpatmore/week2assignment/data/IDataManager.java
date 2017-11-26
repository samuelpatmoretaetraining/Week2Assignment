package com.muelpatmore.week2assignment.data;

import com.muelpatmore.week2assignment.data.network.APIHelper;
import com.muelpatmore.week2assignment.data.network.models.SongModel;

import java.util.ArrayList;

/**
 * Created by Sam on 25/11/2017.
 */

public interface IDataManager extends APIHelper {
    public ArrayList<SongModel> getTrackList(int genre);
}
