package com.dutchtechnologies.news_challenge.di.module

import android.app.Application
import android.content.Context
import com.dutchtechnologies.skyscanner_challenge.di.module.NetworkModule
import com.dutchtechnologies.skyscanner_challenge.di.module.PresentationModule
import com.dutchtechnologies.skyscanner_challenge.di.module.RepositoryModule
import com.dutchtechnologies.skyscanner_challenge.di.module.UseCaseModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        PresentationModule::class,
        NetworkModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        FragmentBuilder::class]
)
abstract class AppModule {
    @Binds
    abstract fun provideApp(application: Application): Context

}