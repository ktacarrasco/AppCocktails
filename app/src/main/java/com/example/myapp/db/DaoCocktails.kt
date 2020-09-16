package com.example.myapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapp.pojo.Cocktails

@Dao
interface DaoCocktails {

    //Insertar un listado
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(listCocktails: List<Cocktails>)

    //agregar fav
    @Update
    suspend fun updateFav(vararg cocktails: Cocktails)


    // traer todos los elementos de la tabla
    @Query("SELECT * FROM Cocktails_table  ORDER BY name_cocktails ASC ")
    fun getAllcocktailsList() : LiveData<List<Cocktails>>

    //traer elemento desde id
    @Query("SELECT * FROM cocktails_table WHERE id=:mId")
    fun getIdList(mId:Int) : LiveData<Cocktails>
}