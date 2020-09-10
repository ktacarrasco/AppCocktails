package com.example.myapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapp.pojo.Cocktails
import com.example.myapp.remote.Repository

class MainViewModel  (application: Application) : AndroidViewModel(application) {
    private val repository =  Repository(application)
    private val cocktailsList = repository.passLiveDataToViewModel()

    fun fetchFromServer() {
        repository.fetchDataFromServer()
    }

    fun getDataFromDB(id: Int): LiveData<List<Cocktails>> {
        return cocktailsList
    }


}