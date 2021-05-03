package edu.sjsu.twitterfeeds.networking

import edu.sjsu.twitterfeeds.BuildConfig
import edu.sjsu.twitterfeeds.models.TweetInformation
import edu.sjsu.twitterfeeds.models.TweetList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.Callable

object Networking {

    val BASE_URL = "https://api.twitter.com/"
    val twitterApis: TwitterApis by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(TwitterApis::class.java)
    }

    interface TwitterApis {
        @Headers(
            "Content-Type: application/json; charset=UTF-8",
            "Authorization: Bearer ${BuildConfig.BEARER_TOKEN}"
        )
        @GET("2/tweets/search/recent?max_results=50")
        fun searchTweets(@Query("query") query: String): Call<TweetList>

        @Headers(
            "Content-Type: application/json; charset=UTF-8",
            "Authorization: Bearer ${BuildConfig.BEARER_TOKEN}"
        )
        @GET("2/tweets?tweet.fields=created_at&expansions=author_id&user.fields=created_at")
        fun getTweetDetails(@Query("ids") tweetIds: String): Call<TweetInformation>
    }
}