package com.example.myapp.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
       // private const val BASE_URL = "https://my-json-server.typicode.com/ktacarrasco/apitragos/"
       private const val BASE_URL = "https://demo8845745.mockable.io/"

        fun retrofitInstance(): Api {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}