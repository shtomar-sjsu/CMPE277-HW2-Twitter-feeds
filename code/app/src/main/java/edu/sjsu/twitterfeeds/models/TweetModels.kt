package edu.sjsu.twitterfeeds.models

import com.squareup.moshi.Json

data class TweetList(
    @field:Json(name = "data") val data: List<TweetListItem>
)

data class TweetListItem(
    @field:Json(name = "text") val tweet: String,
    @field:Json(name = "id") val tweetId: String
)

data class TweetInformation(
    @field:Json(name = "data") val tweetsDetailsList: List<TweetDetails>,
    @field:Json(name = "includes") val users: UsersInfo
)

data class UsersInfo(
    @field:Json(name = "users") val usersList: List<UserDetails>
)

data class TweetDetails(
    @field:Json(name = "text") val tweet: String,
    @field:Json(name = "author_id") val authorId: String,
    @field:Json(name = "created_at") val creationDate: String
)

data class UserDetails(
    @field:Json(name = "username") val userName: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "created_at") val userCreationDate: String
)