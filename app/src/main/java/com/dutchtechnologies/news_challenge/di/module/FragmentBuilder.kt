package com.dutchtechnologies.news_challenge.di.module

import com.dutchtechnologies.news_challenge.SourcesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): SourcesFragment
}