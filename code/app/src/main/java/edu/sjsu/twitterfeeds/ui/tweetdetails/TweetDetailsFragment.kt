package edu.sjsu.twitterfeeds.ui.tweetdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import edu.sjsu.twitterfeeds.R
import edu.sjsu.twitterfeeds.databinding.TweetDetailsFragmentBinding
import edu.sjsu.twitterfeeds.models.TweetInformation
import edu.sjsu.twitterfeeds.ui.FragmentWithLoadingDialogs

class TweetDetailsFragment : FragmentWithLoadingDialogs() {

    companion object {
        val TWEET_ID_KEY = "TWEET_ID_KEY"
    }

    private lateinit var viewModel: TweetDetailsViewModel
    private lateinit var binding: TweetDetailsFragmentBinding
    private var tweetDetailsObserver: Observer<TweetInformation?>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.tweet_details_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TweetDetailsViewModel::class.java)
        arguments?.getString(TWEET_ID_KEY)?.let { tweetId ->
            showLoadingDialog()
            tweetDetailsObserver = Observer { tweetInformation ->
                hideLoadingDialog()
                if (tweetInformation == null) {
                    showErrorMessage("Error occurred in fetching Tweet details")
                } else {
                    binding.tweetInformation = TweetInformationFormatter(tweetInformation)
                    showSuccessMessage("Successfully fetched Tweet details")
                }
            }
            viewModel.liveDataTweetDetails.observe(viewLifecycleOwner, tweetDetailsObserver!!)
            viewModel.getTweetDetails(tweetId)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tweetDetailsObserver?.let {
            viewModel.liveDataTweetDetails.removeObserver(it)
        }
    }
}