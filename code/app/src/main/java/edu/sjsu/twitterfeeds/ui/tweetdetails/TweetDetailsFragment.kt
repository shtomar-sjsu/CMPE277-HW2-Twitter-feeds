package edu.sjsu.twitterfeeds.ui.tweetdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import edu.sjsu.twitterfeeds.R
import edu.sjsu.twitterfeeds.databinding.TweetDetailsFragmentBinding
import edu.sjsu.twitterfeeds.ui.FragmentWithLoadingDialogs

class TweetDetailsFragment : FragmentWithLoadingDialogs() {

    private lateinit var viewModel: TweetDetailsViewModel
    private lateinit var binding: TweetDetailsFragmentBinding

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
    }
}