package com.example.myapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapp.pojo.Cocktails
import com.example.myapp.remote.Repository
import kotlinx.coroutines.launch

class MainViewModel  (application: Application) : AndroidViewModel(application) {
    private val repository =  Repository(application)
    private val cocktailsList = repository.passLiveDataToViewModel()
    private val cocktailsFavList = repository.passLiveFavDataToViewModel()

    fun fetchFromServer() {
        repository.fetchDataFromServer()
    }

    //aregar favorito
    fun updateFav(cocktails: Cocktails) = viewModelScope.launch {
        repository.updateFav(cocktails)
    }

    //favorito
    fun getFavFromDB(id: Int): LiveData<List<Cocktails>> {
        return cocktailsFavList
    }


    fun getDataFromDB(id: Int): LiveData<List<Cocktails>> {
        return cocktailsList
    }

    fun getIdDataFromDB(id: Int): LiveData<Cocktails> {
        return repository.passIdtoFragment(id)
    }


}