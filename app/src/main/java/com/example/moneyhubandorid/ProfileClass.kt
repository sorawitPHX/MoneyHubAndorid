package com.example.moneyhubandorid

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ProfileClass(
    @Expose
    @SerializedName("IDuser") val iduser: Int,

    @Expose
    @SerializedName("Firstname") val firstname: String,

    @Expose
    @SerializedName("Lastname") val lastname: String,

    @Expose
    @SerializedName("Birthday") val birthday: String,

    @Expose
    @SerializedName("Photo_path") val profile_photo_path: String,

    @Expose
    @SerializedName("Career") val career: String,

    @Expose
    @SerializedName("Gender") val gender: String,
)
