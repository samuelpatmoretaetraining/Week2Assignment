package com.muelpatmore.week2assignment.messages;

import com.muelpatmore.week2assignment.data.network.models.SongModel;

import java.util.ArrayList;

/**
 * Created by Samuel on 27/11/2017.
 */

public class TrackListMessage {

    public final ArrayList<SongModel> trackList;

    public TrackListMessage(ArrayList<SongModel> trackList) {
        this.trackList = trackList;
    }
}
