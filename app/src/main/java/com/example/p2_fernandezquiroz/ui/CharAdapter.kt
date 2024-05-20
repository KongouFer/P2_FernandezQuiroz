package com.example.p2_fernandezquiroz.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.p2_fernandezquiroz.data.remote.model.CharacterDta
import com.example.p2_fernandezquiroz.databinding.AvatarElementBinding

class CharAdapter(
    private val personajes: List<CharacterDta>,
    private val onCharacterClicked: (CharacterDta) -> Unit
): RecyclerView.Adapter<CharViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharViewHolder {
       val binding = AvatarElementBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CharViewHolder(binding)
    }

    override fun getItemCount(): Int = personajes.size

    override fun onBindViewHolder(holder: CharViewHolder, position: Int) {
        val personaje = personajes[position]
        holder.bind(personaje)
        Glide.with(holder.itemView.context)
            .load(personaje.imagen)
            .into(holder.ivThumbnail)
        holder.itemView.setOnClickListener {
            onCharacterClicked(personaje)
        }
    }
}