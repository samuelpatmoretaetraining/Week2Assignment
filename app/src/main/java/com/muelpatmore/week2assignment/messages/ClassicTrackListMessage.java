package com.muelpatmore.week2assignment.messages;

import com.muelpatmore.week2assignment.data.network.models.SongModel;

import java.util.ArrayList;

/**
 * Created by Sam on 26/11/2017.
 */

public class ClassicTrackListMessage {

    public final ArrayList<SongModel> trackList;

    public ClassicTrackListMessage(ArrayList<SongModel> trackList) {
        this.trackList = trackList;//
    }

}
