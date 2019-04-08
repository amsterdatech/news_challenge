package com.dutchtechnologies.news_challenge.di.module

import com.dutchtechnologies.news_challenge.articles.NewsFragment
import com.dutchtechnologies.news_challenge.articles.SourcesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector
    abstract fun contributeSourcesFragment(): SourcesFragment

    @ContributesAndroidInjector
    abstract fun contributeNewsFragment(): NewsFragment
}