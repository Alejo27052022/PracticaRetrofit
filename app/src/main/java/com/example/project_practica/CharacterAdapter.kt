package com.example.project_practica

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.project_practica.data.Datos
import androidx.recyclerview.widget.RecyclerView

class CharacterAdapter(private val characters: List<Datos>):
        RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(){

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item, parent, false)
                return CharacterViewHolder(view)
            }

            override fun onBindViewHolder(holder: CharacterViewHolder, position: Int){
                holder.nameData.text = characters[position].name
            }

            override fun getItemCount(): Int = characters.size

            inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
                val nameData: TextView = itemView.findViewById(R.id.text1)
            }

        }