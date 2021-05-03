package edu.sjsu.twitterfeeds.ui.tweetdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.sjsu.twitterfeeds.models.TweetInformation
import edu.sjsu.twitterfeeds.networking.Networking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TweetDetailsViewModel : ViewModel() {

    var liveDataTweetDetails = MutableLiveData<TweetInformation?>()

    fun getTweetDetails(tweetId: String) {
        Networking.twitterApis.getTweetDetails(tweetId)
            .enqueue(object : Callback<TweetInformation> {
                override fun onResponse(
                    call: Call<TweetInformation>,
                    response: Response<TweetInformation?>
                ) {
                    if (response.isSuccessful) {
                        liveDataTweetDetails.value = response.body()
                    } else {
                        liveDataTweetDetails.value = null
                    }
                }

                override fun onFailure(call: Call<TweetInformation>, t: Throwable) {
                    liveDataTweetDetails.value = null
                }
            })
    }
}