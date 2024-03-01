package com.example.moneyhubandorid.api

import com.example.moneyhubandorid.Career
import com.example.moneyhubandorid.Gender
import com.example.moneyhubandorid.LoginClass
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

    @GET("allCareers")
    fun retrieveCareers(): Call<List<Career>>

    @GET("allGenders")
    fun retrieveGenders(): Call<List<Gender>>



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