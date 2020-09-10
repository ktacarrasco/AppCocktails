package com.example.myapp.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.pojo.Cocktails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cocktails.view.*

class Adapter (var mdataSetP: List<Cocktails>, var listenerS: MainFragment): RecyclerView.Adapter<Adapter.photoHolder>() {

    fun updateData(listCocktails: List<Cocktails>) {
        Log.d("UPDATE", "update ${listCocktails.size}")
        mdataSetP = listCocktails
        notifyDataSetChanged()
    }
    class photoHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val titleTv= itemView.titleTV
        val photoTv= itemView.photoTV

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): photoHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cocktails, parent, false)

        return photoHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("Cantidad",mdataSetP.size.toString())
        return mdataSetP.size
    }

    override fun onBindViewHolder(holder: photoHolder, position: Int) {
        val photo =  mdataSetP[position]

        val titletv ="${photo.name_cocktails}"

        holder.titleTv.text = titletv

        Picasso.get()
            .load(photo.url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.photoTv)
    }

    interface MyClickListener {

        fun onItemClick(cocktails: Cocktails)
    }
}