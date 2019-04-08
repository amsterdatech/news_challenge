package com.dutchtechnologies.news_challenge.model

import android.os.Parcel
import android.os.Parcelable

data class SearchRequestForm(
    val apiKey: String = "",
    val sources: String = "",
    var pageIndex: Int = 1
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(), parcel.readString()

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(apiKey)
        parcel.writeString(sources)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchRequestForm> {
        override fun createFromParcel(parcel: Parcel): SearchRequestForm {
            return SearchRequestForm(parcel)
        }

        override fun newArray(size: Int): Array<SearchRequestForm?> {
            return arrayOfNulls(size)
        }
    }
}