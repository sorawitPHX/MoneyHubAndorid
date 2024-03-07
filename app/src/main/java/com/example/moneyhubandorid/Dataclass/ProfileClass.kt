package com.example.moneyhubandorid.Dataclass

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


data class ProfileClass(
    @Expose
    @SerializedName("IDuser") val IDuser: Int,

    @Expose
    @SerializedName("Firstname") val Firstname: String,

    @Expose
    @SerializedName("Lastname") val Lastname: String,

    @Expose
    @SerializedName("Birthday") val Birthday: String,

    @Expose
    @SerializedName("Photo_path") val Photo_path: String,

    @Expose
    @SerializedName("Career") val Career: String,

    @Expose
    @SerializedName("Gender") val Gender: String,
)
