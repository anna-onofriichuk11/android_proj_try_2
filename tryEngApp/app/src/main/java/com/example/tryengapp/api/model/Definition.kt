package com.example.tryengapp.api.model

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.TypeConverters


@Parcelize
@TypeConverters(RoomTypeConverter::class)
data class Definition(
    val definition: String? = "",
    val speech: String? = "",
    val synonym: List<String>? = mutableListOf(),
    val example: List<String>? = mutableListOf()
): Parcelable