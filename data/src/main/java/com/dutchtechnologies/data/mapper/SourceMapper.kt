package com.dutchtechnologies.data.mapper

import com.dutchtechnologies.data.model.SourceEntity
import com.dutchtechnologies.domain.Source
import javax.inject.Inject

open class SourceMapper @Inject constructor() :
    Mapper<SourceEntity, Source> {

    override fun mapFromEntity(type: SourceEntity): Source {
        return Source(type.id, type.name,type.url,type.category)
    }


    override fun mapToEntity(type: Source): SourceEntity {
        return SourceEntity(type.id, type.name,type.url,"","","")
    }


}