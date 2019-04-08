package com.dutchtechnologies.skyscanner_challenge.di.module

import com.dutchtechnologies.data.ArticleDataRepository
import com.dutchtechnologies.domain.interactor.GetArticlesListSingleUseCase
import com.dutchtechnologies.domain.interactor.GetSourcesListSingleUseCase
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    @Named("ioScheduler")
    internal fun provideIoScheduler() = Schedulers.io()

    @Provides
    @Singleton
    @Named("mainThreadScheduler")
    internal fun provideMainThreadScheduler() = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    internal fun provideGetArticlesListSingleUseCase(
        articlesRepository: ArticleDataRepository,
        @Named("ioScheduler") ioScheduler: Scheduler,
        @Named("mainThreadScheduler") mainThreadScheduler: Scheduler
    ): GetArticlesListSingleUseCase =

        GetArticlesListSingleUseCase(articlesRepository, ioScheduler, mainThreadScheduler)

    @Provides
    @Singleton
    internal fun provideGetSourcesListSingleUseCase(
        articlesRepository: ArticleDataRepository,
        @Named("ioScheduler") ioScheduler: Scheduler,
        @Named("mainThreadScheduler") mainThreadScheduler: Scheduler
    ): GetSourcesListSingleUseCase =

        GetSourcesListSingleUseCase(articlesRepository, ioScheduler, mainThreadScheduler)
}