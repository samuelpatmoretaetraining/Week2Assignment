package com.muelpatmore.week2assignment.data.constants;

/**
 * Created by Samuel on 24/11/2017.
 */

public class API_Constants {

    public static final int CLASSIC = 0;
    public static final int ROCK = 1;
    public static final int POP = 2;

    //generalised API link (Classical) https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50
    public static final String BASE_URL= "https://itunes.apple.com/";
    public static final String TAIL_LIST_URL= "&amp;media=music&amp;entity=song&amp;limit=50";



    public static final String CLASSIC_QUERY= "search?term=classic&amp;media=music&amp;entity=song&amp;limit=50";
    public static final String ROCK_QUERY= "search?term=rock&amp;media=music&amp;entity=song&amp;limit=50";
    public static final String POP_QUERY= "search?term=pop&amp;media=music&amp;entity=song&amp;limit=50";

    public static final String[] GENRE_QUERIES = {CLASSIC_QUERY, ROCK_QUERY, POP_QUERY};
}
