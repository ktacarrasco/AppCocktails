package com.example.myapp.remote

import com.example.myapp.pojo.Cocktails
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("tragos")
    fun getTragos(): Call<List<Cocktails>>
}