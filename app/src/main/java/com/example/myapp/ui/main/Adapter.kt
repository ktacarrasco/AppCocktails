package com.example.myapp.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.FavoriteFragment
import com.example.myapp.R
import com.example.myapp.pojo.Cocktails
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cocktails.view.*

class Adapter(var mdataSetP: List<Cocktails>, var listenerS: MyClickListener): RecyclerView.Adapter<Adapter.photoHolder>() {





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

        if (photo.favStatus.equals(true)) {
            holder.itemView.favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
            holder.itemView.favBtn.setSelected(true)
        }
        else{
            holder.itemView.favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
            holder.itemView.favBtn.setSelected(false)}


        holder.itemView.setOnClickListener(View.OnClickListener{

            Toast.makeText(holder.itemView.context,"$titletv", Toast.LENGTH_SHORT).show()
            listenerS.onItemClick(mdataSetP.get(position))


        })

        holder.itemView.favBtn.setOnClickListener(View.OnClickListener {


            if (photo.favStatus.equals(false)) {
                listenerS.favClick(mdataSetP.get(position))
                val snack = Snackbar.make(it,"Enviado a Cocktail Favorito", Snackbar.LENGTH_LONG)
                snack.show()
            }
            else {
                listenerS.desfavClick(mdataSetP.get(position))
            val snack = Snackbar.make(it,"Eliminado de Cocktails Favorito", Snackbar.LENGTH_LONG)
            snack.show()
            }




        })

    }




    interface MyClickListener {

        fun onItemClick(cocktails: Cocktails)
        fun favClick(cocktails: Cocktails)
        fun desfavClick(cocktails: Cocktails)
    }

    interface IAdapter{
        fun getFromAdapter(id:Int)

    }
}