package com.dutchtechnologies.data.remote

import com.dutchtechnologies.data.model.SourceEntity
import javax.inject.Inject

open class SourceEntityMapper @Inject constructor() : EntityMapper<SourceWrapperResponse, List<SourceEntity>> {


    override fun mapFromRemote(type: SourceWrapperResponse): List<SourceEntity> {
        return type.sources.map {
            SourceEntity(it.id, it.name,it.url,it.category,it.language,it.country)
        }
    }
}
