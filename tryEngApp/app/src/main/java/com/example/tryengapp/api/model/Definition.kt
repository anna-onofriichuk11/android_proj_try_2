<<<<<<< HEAD
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
=======
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
>>>>>>> 38b66a03b864ca564c30ccc18f51636406be220a
): Parcelable