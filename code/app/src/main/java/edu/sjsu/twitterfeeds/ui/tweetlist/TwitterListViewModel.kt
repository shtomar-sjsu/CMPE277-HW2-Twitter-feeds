package edu.sjsu.twitterfeeds.ui.tweetlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.sjsu.twitterfeeds.models.TweetList
import edu.sjsu.twitterfeeds.networking.Networking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TwitterListViewModel : ViewModel() {

    var liveDataTweets = MutableLiveData<TweetList?>()

    fun getTweets(searchQuery: String) {
        Networking.twitterApis.searchTweets(createSearchQuery(searchQuery))
            .enqueue(object : Callback<TweetList> {
                override fun onResponse(call: Call<TweetList>, response: Response<TweetList>) {
                    if (response.isSuccessful) {
                        liveDataTweets.value = response.body()
                    } else {
                        liveDataTweets.value = null
                    }
                }

                override fun onFailure(call: Call<TweetList>, t: Throwable) {
                    liveDataTweets.value = null
                }
            })
    }

    private inline fun createSearchQuery(query: String) = "$query (lang:en)"
}