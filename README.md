# Android CMIND News App Clean Architecture

## Requirements of the task


In order to evaluate your skills and coding style we would like you to develop a small application that uses https://newsapi.org API RESTful services.

1. The main screen will load news sources and will then need to present with the following information. You may use any layout.
   * Source title. 
   * Description. 
   * URL.
2. When a user taps on any source within the previous screen the app goes step 3:
3. The user should see list of articles with info. You may use any layout.
    * Article title.
    * Article description.
    * Article thumbnail.
    * Article author.
    * Article published day.

## Procedure

1. Get your API Key as explained within the documentation https://newsapi.org.
2. Use Android Studio or XCode and share the project via email of via Github.
3. Write a few sentences (max. 10) about your approach and send it with your solution.
4. You can use third party libraries with its preference to handle dependencies.
5. Take the opportunity to showcase your coding style and use whatever design pattern
(MVC, MVVM, etc) you would normally have used for this kind of task.


## Criteria

1. Interface style, how you implemented the solution visually
2. Code structure, is the solution scalable
3. Code style, is everything well documented
4. Are all the stated requirements implemented

## Plus

1. Adapt UI to device orientation changes
2. Pagination or load more for the article list
3. Functional programming
4. Unit Testing



## Screenshots

When start app you gonna see:

1. Simple branded splash
2. Simple list of all sources.
3. Simple list of articles filtered by source with pagination

<br/>
<br/>
<img src="https://github.com/amsterdatech/news_challenge/blob/master/art/splash.png" alt="Splash"  style="float: left; width: 33%; margin-right: 1%; margin-bottom: 0.5em; "/>
<img src="https://github.com/amsterdatech/news_challenge/blob/master/art/list_sources.png" alt="Sources"  style="float: left; width: 33%; margin-right: 1%; margin-bottom: 0.5em;" />
<img src="https://github.com/amsterdatech/news_challenge/blob/master/art/list_articles.png" alt="Articles"  style="float: left; width: 32%; margin-right: 1%; margin-bottom: 0.5em;" />
<p style="clear: both;">

## Languages, libraries and tools used

* [Kotlin](https://kotlinlang.org/)
* Android Support/Design Libraries
* [RxJava2](https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0)
* [Dagger 2 (2.17)](https://github.com/google/dagger)
* [Glide](https://github.com/bumptech/glide)
* [Retrofit 2](http://square.github.io/retrofit/)
* [OkHttp 3](http://square.github.io/okhttp/)
* [Gson](https://github.com/google/gson)
* [Mockito](http://site.mockito.org/)
* [Espresso](https://developer.android.com/training/testing/espresso/index.html)

## What I could improve in the future

## What was the most difficult part and why

## Why RxJava2 and why not Coroutines

## Why clean architecture and why MVVM with LiveData and ViewModels

