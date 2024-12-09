package com.idcamp.mysubmissionapps

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val name: String,
    val description: String,
    val photo: Int,
    val releaseDate: String,
    val developer: String,
) : Parcelable
