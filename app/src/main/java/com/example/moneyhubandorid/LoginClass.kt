package com.example.moneyhubandorid

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginClass(
    @Expose
    @SerializedName("success") val success: Int,

    @Expose
    @SerializedName("iduser") val iduser: String
    )
