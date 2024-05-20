package com.example.p2_fernandezquiroz.data.remote.model

import com.google.gson.annotations.SerializedName

data class CharacterDta(
    @SerializedName("allies")
    var allies: String, //
    @SerializedName("enemies")
    var enemies: String,
    @SerializedName("_id")
    var id: String,
    @SerializedName("photoUrl")//
    var imagen: String,
    @SerializedName("name")
    var nombre: String,
    @SerializedName("gender")
    var genero: String,
    @SerializedName("hair")
    var cabello: String,
    @SerializedName("profession")
    var profesion: String,
    @SerializedName("position")
    var posicion: String,
    @SerializedName("affiliation")
    var bando: String,
    @SerializedName("first")
    var primero: String,
)
