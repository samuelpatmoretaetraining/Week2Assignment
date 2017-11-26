package com.muelpatmore.week2assignment.data;

import android.util.Log;
import android.widget.Toast;

import com.muelpatmore.week2assignment.MyMusicApp;
import com.muelpatmore.week2assignment.data.network.APIHelper;
import com.muelpatmore.week2assignment.data.network.AppAPIHelper;
import com.muelpatmore.week2assignment.data.network.models.MusicResultsModel;
import com.muelpatmore.week2assignment.data.network.models.SongModel;
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

public class AppDataManager implements IDataManager {

    private static final String TAG = AppDataManager.class.getSimpleName();

    public static final int CLASSIC = 0;
    public static final int ROCK = 1;
    public static final int POP = 2;

    private AppDataManager mInstance = null;
    private APIHelper mAPIHelper;
    private AppSchedulerProvider mAppSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;

    public AppDataManager() {
        this.mAPIHelper = new AppAPIHelper();
        this.mAppSchedulerProvider = new AppSchedulerProvider();
        this.mCompositeDisposable = new CompositeDisposable();
    }

    public ArrayList<SongModel> getTrackList(int genre) {
        switch (genre) {
            case CLASSIC :
                ArrayList a = getClassicTrackList();
                Log.d(TAG, "getTackList "+a.size());
                return getClassicTrackList();
        }
        return null;
    }
    private ArrayList<SongModel> getClassicTrackList() {
        //ToDo check for data in DB
        if(false) {
            //ToDo get data from db

        } else {
            return getClassicTrackListFromAPI();
        }
        return null;
    }
    private ArrayList<SongModel> getClassicTrackListFromAPI() {
        final ArrayList<SongModel> songList = new ArrayList<>();
        mCompositeDisposable.add(
                getClassicList()
                        .subscribeOn(mAppSchedulerProvider.io())
                        .observeOn(mAppSchedulerProvider.ui())
                        .subscribe(new Consumer<MusicResultsModel>() {
                            @Override
                            public void accept(MusicResultsModel musicResultsModel) throws Exception {
                                songList.addAll(musicResultsModel.getResults());
                                for(SongModel s : songList) {
                                    Log.d(TAG, s.getPrimaryGenreName());
                                }
                                EventBus.getDefault().post(new ClassicTrackListMessage(songList));
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        }));

        Log.i(TAG, "Returning classic song list "+songList.size());
        return songList;
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
}
