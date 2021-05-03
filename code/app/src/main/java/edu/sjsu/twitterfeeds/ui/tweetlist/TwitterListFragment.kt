package edu.sjsu.twitterfeeds.ui.tweetlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.sjsu.twitterfeeds.R
import edu.sjsu.twitterfeeds.adapters.TweetListAdapter
import edu.sjsu.twitterfeeds.databinding.TwitterListFragmentBinding
import edu.sjsu.twitterfeeds.models.TweetList
import edu.sjsu.twitterfeeds.ui.FragmentWithLoadingDialogs
import edu.sjsu.twitterfeeds.ui.tweetdetails.TweetDetailsFragment
import kotlinx.coroutines.*

class TwitterListFragment : FragmentWithLoadingDialogs() {

    private lateinit var viewModel: TwitterListViewModel
    private lateinit var dataBinding: TwitterListFragmentBinding
    private lateinit var tweetListObserver: Observer<TweetList?>
    private lateinit var tweetListAdapter: TweetListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.twitter_list_fragment, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TwitterListViewModel::class.java)
        tweetListObserver = Observer {
            hideLoadingDialog()
            when (it) {
                null -> showErrorMessage("Error in fetching Tweets")
                else -> {
                    tweetListAdapter.tweets = it.tweets
                    tweetListAdapter.notifyDataSetChanged()
                    showSuccessMessage("Tweet successfully refreshed")
                }
            }
        }
        viewModel.liveDataTweets.observe(viewLifecycleOwner, tweetListObserver)
        setUpSearchView()
        setUpRecylerView()
        getTweets("USA")
    }

    private fun setUpRecylerView() {
        tweetListAdapter = TweetListAdapter()
        dataBinding.tweetList.adapter = tweetListAdapter
        tweetListAdapter.tweetClickListener = {
            var arguements = Bundle()
            arguements.putString(TweetDetailsFragment.TWEET_ID_KEY, it.tweetId)
            findNavController().navigate(R.id.action_tweet_deatils, arguements)
        }
    }

    private fun getTweets(searcQuery: String) {
        showLoadingDialog()
        viewModel.getTweets(searcQuery)
    }

    private fun setUpSearchView() {
        dataBinding.tweetsSearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            val scope = CoroutineScope(Dispatchers.Main)
            var searchJob: Job? = null
            override fun onQueryTextSubmit(p0: String?) = false

            override fun onQueryTextChange(query: String?): Boolean {
                searchJob?.cancel()
                searchJob = scope.launch {
                    query?.let {
                        delay(1000)
                        if (it.isNotEmpty()) {
                            getTweets(it)
                        }
                    }
                }
                return false
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.liveDataTweets.removeObserver(tweetListObserver)
    }
}