package com.example.moneyhubandorid

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Career(
    @Expose
    @SerializedName("idcareer") val idcareer: Int,

    @Expose
    @SerializedName("career") val career: String,
): Parcelable
