package edu.sjsu.twitterfeeds.ui.tweetdetails

import edu.sjsu.twitterfeeds.models.TweetInformation

class TweetInformationFormatter(val tweetInformation: TweetInformation) {

    fun getTweet(): String = "Tweet: ${tweetInformation.tweetsDetailsList[0].tweet}"
    fun getCreationDate(): String =
        "Tweet Creation Date: ${tweetInformation.tweetsDetailsList[0].creationDate}"

    fun getUserName(): String = "User Name: ${tweetInformation.users.usersList[0].name}"
    fun getHandleName(): String = "Handle Name: ${tweetInformation.users.usersList[0].userName}"
    fun getUserCreationDate(): String =
        "User Creation date: ${tweetInformation.users.usersList[0].userCreationDate}"
}