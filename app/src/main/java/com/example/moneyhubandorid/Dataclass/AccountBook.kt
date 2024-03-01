package com.example.moneyhubandorid.Dataclass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AccountBook(
    @Expose
    @SerializedName("idaccount_book") val idaccount_book: Int,

    @Expose
    @SerializedName("iduser") val iduser: Int,

    @Expose
    @SerializedName("account_book") val account_book: String,

    @Expose
    @SerializedName("balance") val balance: Int,

    @Expose
    @SerializedName("account_photo_path") val account_photo_path: Int,
)
