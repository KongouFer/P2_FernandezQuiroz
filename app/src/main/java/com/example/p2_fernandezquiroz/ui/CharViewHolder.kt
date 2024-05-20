package com.example.p2_fernandezquiroz.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.p2_fernandezquiroz.data.remote.model.CharacterDta
import com.example.p2_fernandezquiroz.databinding.ActivityMainBinding
import com.example.p2_fernandezquiroz.databinding.AvatarElementBinding

class CharViewHolder(
    private val binding: AvatarElementBinding
): RecyclerView.ViewHolder(binding.root) {
    val ivThumbnail = binding.ivThumbnail
    fun bind(personaje: CharacterDta){
        binding.tvNombre.text = personaje.nombre
        binding.tvAfiliacion.text = personaje.bando
    }
}