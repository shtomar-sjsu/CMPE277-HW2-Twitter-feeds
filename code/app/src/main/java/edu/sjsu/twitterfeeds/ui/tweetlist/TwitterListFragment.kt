package edu.sjsu.twitterfeeds.ui.tweetlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import edu.sjsu.twitterfeeds.R
import edu.sjsu.twitterfeeds.databinding.TwitterListFragmentBinding
import edu.sjsu.twitterfeeds.ui.FragmentWithLoadingDialogs

class TwitterListFragment : FragmentWithLoadingDialogs() {

    private lateinit var viewModel: TwitterListViewModel
    private lateinit var dataBinding: TwitterListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.twitter_list_fragment, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TwitterListViewModel::class.java)
    }
}