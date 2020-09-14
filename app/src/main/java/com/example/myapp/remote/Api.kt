package com.example.myapp.remote

import com.example.myapp.pojo.Cocktails
import com.example.myapp.pojo.Tragos
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("apicocktails")
    fun getTragos(): Call<Tragos>
    //fun getTragos(): Call<List<Cocktails>>
}