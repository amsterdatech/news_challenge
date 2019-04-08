package com.dutchtechnologies.news_challenge.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module(
    includes = [ActivityBuilder::class,
        FragmentBuilder::class]
)
abstract class AppModule {
    @Binds
    abstract fun provideApp(application: Application): Context

}