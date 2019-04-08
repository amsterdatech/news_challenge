package com.dutchtechnologies.skyscanner_challenge.di.module

import com.dutchtechnologies.domain.interactor.GetArticlesListSingleUseCase
import com.dutchtechnologies.domain.interactor.GetSourcesListSingleUseCase
import com.dutchtechnologies.news_challenge.mapper.SearchRequestMapper
import com.dutchtechnologies.news_challenge.presentation.ArticlesPresenter
import com.dutchtechnologies.news_challenge.presentation.SourcesPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {


    @Provides
    internal fun provideArticlesPresenter(
        getArticles: GetArticlesListSingleUseCase,
        mapper: SearchRequestMapper
    ): ArticlesPresenter {
        return ArticlesPresenter(getArticles, mapper)
    }


    @Provides
    internal fun provideSourcesPresenter(
        getSources: GetSourcesListSingleUseCase,
        mapper: SearchRequestMapper
    ): SourcesPresenter {
        return SourcesPresenter(getSources, mapper)
    }
}