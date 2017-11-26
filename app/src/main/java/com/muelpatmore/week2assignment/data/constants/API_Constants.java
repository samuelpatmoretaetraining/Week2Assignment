package com.muelpatmore.week2assignment.data.constants;

/**
 * Created by Samuel on 24/11/2017.
 */

public class API_Constants {

    public static final String[] GENRES = {"classic", "rock", "classic"};

    //generalised API link (Classical) https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50
    public static final String BASE_URL= "https://itunes.apple.com/";
    public static final String TAIL_LIST_URL= "&amp;media=music&amp;entity=song&amp;limit=50";

    public static final String CLASSIC = "classic";
    public static final String ROACK ="rock";
    public static final String POP = "pop";

    public static final String QUERY_START = "search?term=";
    public static final String QUERY_END = "&amp;media=music&amp;entity=song&amp;limit=50";


    public static final String CLASSIC_QUERY= "search?term=classic&amp;media=music&amp;entity=song&amp;limit=50";
    public static final String ROCK_QUERY= "search?term=rock&amp;media=music&amp;entity=song&amp;limit=50";
    public static final String POP_QUERY= "search?term=pop&amp;media=music&amp;entity=song&amp;limit=50";

    public static final String[] GENRE_QUERIES = {CLASSIC_QUERY, ROCK_QUERY, POP_QUERY};

    //prevent instatication of this resource class
    private API_Constants(){}
}
