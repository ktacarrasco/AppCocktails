package com.example.myapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapp.pojo.Cocktails

@Dao
interface DaoCocktails {

    //Insertar un listado
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(listCocktails: List<Cocktails>)

    // traer todos los elementos de la tabla
    @Query("SELECT * FROM Cocktails_table  ORDER BY name_cocktails ASC ")
    fun getAllcocktailsList() : LiveData<List<Cocktails>>
}