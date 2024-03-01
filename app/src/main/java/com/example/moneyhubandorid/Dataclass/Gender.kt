package com.example.moneyhubandorid.Dataclass

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Gender(
    @Expose
    @SerializedName("idgender") val idcareer: Int,

    @Expose
    @SerializedName("gender") val career: String,
)
