package com.dutchtechnologies.news_challenge.di.module

import com.dutchtechnologies.news_challenge.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [FragmentBuilder::class])
    abstract fun contributeHomeActivity(): HomeActivity
}