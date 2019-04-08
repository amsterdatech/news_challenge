package com.dutchtechnologies.skyscanner_challenge.di.module

import com.dutchtechnologies.data.ArticleDataRepository
import com.dutchtechnologies.data.mapper.ArticleMapper
import com.dutchtechnologies.data.mapper.SearchFormMapper
import com.dutchtechnologies.data.mapper.SourceMapper
import com.dutchtechnologies.data.remote.ArticleEntityMapper
import com.dutchtechnologies.data.remote.ArticleRemoteImpl
import com.dutchtechnologies.data.remote.NewsApiService
import com.dutchtechnologies.data.remote.SourceEntityMapper
import com.dutchtechnologies.data.source.ArticleDataStoreFactory
import com.dutchtechnologies.domain.ArticleRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideArticleRepository(
        factory: ArticleDataStoreFactory,
        mapper: ArticleMapper,
        sourceMapper: SourceMapper,
        searchMapper: SearchFormMapper
    ): ArticleRepository = ArticleDataRepository(factory, mapper, sourceMapper, searchMapper)

    @Provides
    @Singleton
    fun provideArticleRemote(
        gson: Gson,
        service: NewsApiService,
        factory: ArticleEntityMapper,
        sourceMapper: SourceEntityMapper
    ): ArticleRemoteImpl {
        return ArticleRemoteImpl(gson, service, factory, sourceMapper)
    }


}
