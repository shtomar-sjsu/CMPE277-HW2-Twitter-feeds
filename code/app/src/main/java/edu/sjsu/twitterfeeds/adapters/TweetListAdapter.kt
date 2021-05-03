package edu.sjsu.twitterfeeds.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import edu.sjsu.twitterfeeds.R
import edu.sjsu.twitterfeeds.databinding.TweetListItemBinding
import edu.sjsu.twitterfeeds.models.TweetListItem

class TweetListAdapter : RecyclerView.Adapter<TweetListAdapter.TweetListViewHolder>() {

    var tweets: List<TweetListItem> = arrayListOf()
    var tweetClickListener: ((TweetListItem) -> Unit)? = null

    inner class TweetListViewHolder(val binding: TweetListItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = itemView.tag as Int
            tweetClickListener?.invoke(tweets[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetListViewHolder {
        var binding = DataBindingUtil.inflate<TweetListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.tweet_list_item,
            parent,
            false
        )
        return TweetListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TweetListViewHolder, position: Int) {
        holder.itemView.tag = position
        holder.binding.tweet = tweets[position]
    }

    override fun getItemCount(): Int = tweets.count()
}