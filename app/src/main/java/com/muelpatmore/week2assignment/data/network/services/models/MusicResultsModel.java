package com.muelpatmore.week2assignment.data.network.services.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sam on 25/11/2017.
 */

public class MusicResultsModel {

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<SongModel> results = null;

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<SongModel> getResults() {
        return results;
    }

    public void setResults(List<SongModel> results) {
        this.results = results;
    }

}