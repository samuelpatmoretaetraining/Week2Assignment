package com.muelpatmore.week2assignment.data.realm;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Samuel on 26/11/2017.
 */

public interface IRealmHelper {
    public long getLastUpdate(String genre);
    public void saveSongList(ArrayList<RealmSong> realmSongList, String genre);
    public ArrayList<RealmSong> getSongList(String genre);
}
