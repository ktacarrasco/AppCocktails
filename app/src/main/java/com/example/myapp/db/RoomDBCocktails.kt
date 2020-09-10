package com.example.myapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapp.pojo.Cocktails


    @Database(entities = [Cocktails::class], version = 1,exportSchema = false)
    abstract class RoomDBCocktails : RoomDatabase(){


        abstract fun barDao() : DaoCocktails

        companion object {
            @Volatile
            private var INSTANCE: RoomDBCocktails? = null

            fun getDatabase(context: Context): RoomDBCocktails {
                val tempInstance = INSTANCE
                if (tempInstance != null) {
                    return tempInstance
                }
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDBCocktails::class.java,
                        "Cocktails_table")
                        .build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
