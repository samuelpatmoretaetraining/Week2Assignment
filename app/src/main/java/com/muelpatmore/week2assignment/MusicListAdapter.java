package com.muelpatmore.week2assignment;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.muelpatmore.week2assignment.data.network.services.models.SongModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sam on 25/11/2017.
 */

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.SongItemViewHolder> {

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
    public SongItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(applicationContext).inflate(R.layout.song_list_card, parent, false);
        return new SongItemViewHolder(view);
    }

    /**
     *
     * @param holder SongItemViewHolder
     * @param position int index of view element in Recycler view.
     */
    @Override
    public void onBindViewHolder(SongItemViewHolder holder, final int position) {
        Log.i(TAG, "Creating view "+position);
        final SongModel song = songList.get(position);
        holder.tvTrack.setText(song.getTrackName());
        holder.tvArtist.setText(song.getArtistName());
        holder.tvPrice.setText(song.getTrackPrice().toString());
        Picasso.with(holder.ivAlbumArt.getContext()).load(song.getArtworkUrl60()).into(holder.ivAlbumArt);

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // ToDo play audio from main activity (EasyBus message)
                Log.i(TAG, "Track "+position+" clicked (id "+song.getTrackId()+")");
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Track "+position+" clicked (id "+song.getTrackId()+")");
                try {
                    String trackInfo = "Playing "+song.getTrackName()+" by "+song.getArtistName();
                    Log.i(TAG, trackInfo);
                    MediaPlayer mp = new MediaPlayer();
                    mp.setDataSource(song.getPreviewUrl());
                    mp.prepare();
                    mp.start();
                    Toast.makeText(v.getContext(), trackInfo, Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    /**
     * Expands the song_list_card.xml card layout and bind the internal views to Java class variables.
     */
    public class SongItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvTrack, tvArtist, tvPrice;
        ImageView ivAlbumArt;

        public SongItemViewHolder(View itemView) {
            super(itemView);
            tvTrack = (TextView) itemView.findViewById(R.id.tvTrack);
            tvArtist = (TextView) itemView.findViewById(R.id.tvArtist);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            ivAlbumArt = (ImageView) itemView.findViewById(R.id.ivAlbumArt);
        }
    }
}
