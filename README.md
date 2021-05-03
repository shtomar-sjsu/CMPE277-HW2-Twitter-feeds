# CMPE277 HW#2 Twitter-feeds

## About The Project
**Topic5 Android**: An Android application that display the tweets fetched from the Twitter APIs based on the search query entered by the user. The tweets are shown in the form of List View. The app also contains are SearchView where used can enter a search keyword to see the relevant tweets. User can click on a tweet to open a Tweet details View.

## Android Tech Stack
- **Retrofit**: For making network calls
- **Moshi**: For Json de serialization into data models
- **Architecture**: MVVM(Model View ViewModel)
- **LiveData**: For sending events from View Model to View
- **Data Binding**: For Binding View with Fragments and binding data with Layouts
- **Navigation UI**: For showing Tweets List and Tweet details screen
- **Coroutines**: For SearchView Query debounce, so that search will trigger only if user has waited one second after typing search query

## Code 
The Android app source code can be find in the directory **code**.

## App Architecure
- The app uses MVVM Architecture for separating View and Business Logic code.  
- The app uses Navigation UI for navigating between Tweets List and Tweet details screen.  
- The app contains MainActivity, which is the launch activity of app. The root fragment of MainActivity is TwitterListFragment. As soon as the TwitterListFragment loads it triggers the network reuqest to Twitter API to fetch results for search keyword "USA". If the search query is successful it shows the list of tweets fetched from Twitter API. If there is any error in fecthing the Tweets spp shows an error dialog.  
- SearchView: Twitter List fragment also contains a SearchView above Tweet List RecyclerView where user can enter a search keyword for refreshing the Tweet List. Here **Coroutines** is used for waiting for one second after user finished typing the search keyword. App make network request for fetching tweets only after waiting for one second. This is implemented so that app won't make network request for every character that user type in the SearcView.
- When the User click on any Tweet in The Tweet List, user is navigated to TweetDetailsFragment using NavigationController. The TweetId is passed with arguements to TweetDetailsFrahment from TweetListFragment. When the TweetDetailsFragment launches, It uses the TweetId to make network request for fetching the Tweet details. If the Tweet details are successfully fetched they will be presented to the used along with sucess message. If there is any error in fetching the tweet details, an error message will be shown to the used using AlertDialog.

## Packages
- **edu.sjsu.twitterfeeds.adapter**: Contains the TweetList RecyclerView Adapter.
- **edu.sjsu.twitterfeeds.models**: Contains the data classes. These classes are used by Moshi for deserializing network response JSON payload.
- **edu.sjsu.twitterfeeds.networking**: Contains the Networking classes used by the Retrofit.
- **edu.sjsu.twitterfeeds.ui.tweetlist**: Contains the TweetList fragment and it's View model.
- **edu.sjsu.twitterfeeds.ui.tweetdetails**: Contains the TweetDetails fragment and it's View model.

## Twitter
The twitter web API https://api.twitter.com/ is used for searching the tweets and getting the tweet details. The Retrofit and Moshi implementation for netwoking and JSON serialization can be seen in [Networking](https://github.com/shtomar-sjsu/CMPE277-HW2-Twitter-feeds/blob/main/code/app/src/main/java/edu/sjsu/twitterfeeds/networking/Networking.kt) class.

## App demo
A video demonstrating the application use can be seen on [Google drive]()

> The app demo video is only shared with Google accounts with sjsu.edu domain. Please login into google drive with sjsu.edu email id for watching the video.


