package com.example.myapp.remote


import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myapp.db.RoomDBCocktails
import com.example.myapp.pojo.Cocktails
import com.example.myapp.pojo.Tragos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback


class Repository(context: Context) {

    private val tag = "CocktailsRepository"

    //esto viene  de la Base de datos
    private val db: RoomDBCocktails = RoomDBCocktails.getDatabase(context)
    private val cocktailsList = db.barDao().getAllcocktailsList()

    fun passLiveDataToViewModel(): LiveData<List<Cocktails>> {
        return cocktailsList
    }

    fun  passIdtoFragment(id :Int):LiveData<Cocktails>{

        return  db.barDao().getIdList(id)
    }

    // esto hace la llamada a retrofit
    fun fetchDataFromServer() {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getTragos()


        call.enqueue(object : Callback<Tragos> {
            override fun onResponse(call: Call<Tragos>, response: retrofit2.Response<Tragos>) {
                Log.d(tag, response.body().toString())
                CoroutineScope(Dispatchers.IO).launch {

                    db.barDao().insertAll(response.body()?.tragos!!)
                }
            }

            override fun onFailure(call: Call<Tragos>, t: Throwable) {
                Log.d(tag, t.message.toString())

            }
        })

    }
}