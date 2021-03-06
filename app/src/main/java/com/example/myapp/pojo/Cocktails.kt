package com.example.myapp.pojo

import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Cocktails_table")
data class Cocktails (@PrimaryKey() @NonNull val  id : Int,
val name_cocktails: String,
val ingredients: String,
val preparation: String,
val url: String,
var favStatus: Boolean)


    /*(@PrimaryKey() @NonNull val  id : Int,
    val name_cocktails: String,
    val ingredients: String,
    val preparation: String,
    val url: String)*/
