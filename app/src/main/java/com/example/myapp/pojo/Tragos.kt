package com.example.myapp.pojo

import androidx.annotation.NonNull
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Tragos (
    @SerializedName("Tragos")
    val  tragos: List<Cocktails> ) {
}