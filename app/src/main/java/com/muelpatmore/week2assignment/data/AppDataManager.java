package com.muelpatmore.week2assignment.data;

import android.util.Log;
import android.widget.Toast;

import com.muelpatmore.week2assignment.MyMusicApp;
import com.muelpatmore.week2assignment.data.network.APIHelper;
import com.muelpatmore.week2assignment.data.network.AppAPIHelper;
import com.muelpatmore.week2assignment.data.network.models.MusicResultsModel;
import com.muelpatmore.week2assignment.data.network.models.SongModel;
import com.muelpatmore.week2assignment.data.realm.AppRealmHelper;
import com.muelpatmore.week2assignment.data.realm.IRealmHelper;
import com.muelpatmore.week2assignment.data.realm.RealmAdapter;
import com.muelpatmore.week2assignment.data.realm.RealmSong;
import com.muelpatmore.week2assignment.messages.ClassicTrackListMessage;
import com.muelpatmore.week2assignment.ui.utils.rx.AppSchedulerProvider;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Sam on 25/11/2017.
 */

public class AppDataManager implements IDataManager, IRealmHelper {

    private static final String TAG = AppDataManager.class.getSimpleName();

    public static final String CLASSIC = "classic";
    public static final String ROCK = "rock";
    public static final String POP = "pop";

    private AppDataManager mInstance = null;
    private APIHelper mAPIHelper;
    private AppSchedulerProvider mAppSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;
    private IRealmHelper mRealmHelper;

    private long timeofLastRequest = 0;

    public AppDataManager() {
        this.mAPIHelper = new AppAPIHelper();
        this.mAppSchedulerProvider = new AppSchedulerProvider();
        this.mCompositeDisposable = new CompositeDisposable();
        this.mRealmHelper = new AppRealmHelper();
    }

    public ArrayList<SongModel> getTrackList(String genre) {

        switch (genre) {
            case CLASSIC :
                ArrayList a = getClassicTrackList();
                Log.d(TAG, "getTrackList "+a.size());
                return getClassicTrackList();
        }
        return null;
    }
    private ArrayList<SongModel> getClassicTrackList() {
        long timeSinceUpdate = System.currentTimeMillis() - getLastUpdate(CLASSIC);
        Log.i(TAG,CLASSIC+" data last updated "+timeSinceUpdate+"ms ago");

        if(timeSinceUpdate < 20000) {
            Log.i(TAG, CLASSIC +" data loaded from RealmDatabase");
            return getTrackList(CLASSIC);

        } else {
            return getClassicTrackListFromAPI();
        }
    }
    private ArrayList<SongModel> getClassicTrackListFromAPI() {
        final ArrayList<SongModel> apiSongList = new ArrayList<>();
        mCompositeDisposable.add(
                getClassicList()
                        .subscribeOn(mAppSchedulerProvider.io())
                        .observeOn(mAppSchedulerProvider.ui())
                        .subscribe(new Consumer<MusicResultsModel>() {
                            @Override
                            public void accept(MusicResultsModel musicResultsModel) throws Exception {
                                apiSongList.addAll(musicResultsModel.getResults());
                                ArrayList<RealmSong> realmSongList = RealmAdapter.apiDataListToRealmObject(apiSongList, CLASSIC);
                                updateRealm(realmSongList, CLASSIC);
                                EventBus.getDefault().post(new ClassicTrackListMessage(apiSongList));
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        }));

        Log.i(TAG, "Returning classic song list "+apiSongList.size());
        return apiSongList;
    }

    private void updateRealm(ArrayList<RealmSong> songModels, String genre) {
        mRealmHelper.saveSongList(songModels, genre);
    }

    @Override
    public Observable<MusicResultsModel> getClassicList() {
        return mAPIHelper.getClassicList();
    }

    @Override
    public Observable<MusicResultsModel> getRockList() {
        return mAPIHelper.getRockList();
    }

    @Override
    public Observable<MusicResultsModel> getPopList() {
        return null;
    }


    @Override
    public long getLastUpdate(String genre) {
        return mRealmHelper.getLastUpdate(genre);
    }

    @Override
    public void saveSongList(ArrayList<RealmSong> realmSongList, String genre) {
        mRealmHelper.saveSongList(realmSongList, genre);
    }

    @Override
    public ArrayList<RealmSong> getSongList(String genre) {
        return mRealmHelper.getSongList(genre);
    }
}
