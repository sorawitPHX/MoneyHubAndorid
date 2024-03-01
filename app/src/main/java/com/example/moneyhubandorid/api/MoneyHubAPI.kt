package com.example.moneyhubandorid.api

import com.example.moneyhubandorid.Dataclass.AccountBook
import com.example.moneyhubandorid.Dataclass.Career
import com.example.moneyhubandorid.Dataclass.Gender
import com.example.moneyhubandorid.LoginClass
import com.example.moneyhubandorid.Dataclass.ProfileClass
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MoneyHubAPI {
    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<LoginClass>

    @FormUrlEncoded
    @POST("insertAccount")
    fun registerUser(
        @Field("firstname") firstname: String,
        @Field("lastname") lastname: String,
        @Field("birthday") birthday: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("idcareer") idcareer: Int,
        @Field("idgender") idgender: Int
    ): Call<LoginClass>

    @FormUrlEncoded
    @POST("getUser")
    fun getUser(
        @Field("email") email: String
    ): Call<ProfileClass>

    @GET("allCareers")
    fun retrieveCareers(): Call<List<Career>>

    @GET("allGenders")
    fun retrieveGenders(): Call<List<Gender>>

    @FormUrlEncoded
    @POST("insertBookofAccount")
    fun addBookOfAccount(

    )

    @GET("allBookofAccount/{iduser}")
    fun allBookofAccount(
        @Path("iduser") iduser: String
    ): Call<List<AccountBook>>

    companion object {
        fun create(): MoneyHubAPI {
            val Client: MoneyHubAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoneyHubAPI::class.java)
            return Client
        }
    }
}