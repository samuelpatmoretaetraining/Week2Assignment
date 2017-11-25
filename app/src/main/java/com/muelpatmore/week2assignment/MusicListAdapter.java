package com.muelpatmore.week2assignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.muelpatmore.week2assignment.data.network.services.models.SongModel;

import java.util.ArrayList;

/**
 * Created by Sam on 25/11/2017.
 */

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    private static final String TAG= "MusicListAdapter";

    private ArrayList<SongModel> songList;
    private int row_song;
    private Context applicationContext;

    public MusicListAdapter(ArrayList<SongModel> songList, int row_song, Context applicationContext) {
        this.songList = songList;
        this.row_song = row_song;
        this.applicationContext = applicationContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(applicationContext).inflate(R.layout.song_list_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i(TAG, "Creating view "+position);
        SongModel song = songList.get(position);
        holder.tvTrack.setText(song.getTrackName());
        holder.tvArtist.setText(song.getArtistName());
        holder.tvPrice.setText(song.getTrackPrice().toString());
        //ToDo get album art image
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTrack, tvArtist, tvPrice;
        ImageView ivAlbumArt;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTrack = (TextView) itemView.findViewById(R.id.tvTrack);
            tvArtist = (TextView) itemView.findViewById(R.id.tvArtist);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            ivAlbumArt = (ImageView) itemView.findViewById(R.id.ivAlbumArt);
        }
    }
}
