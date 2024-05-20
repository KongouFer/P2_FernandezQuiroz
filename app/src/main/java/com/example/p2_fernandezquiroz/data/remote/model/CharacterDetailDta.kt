package com.example.p2_fernandezquiroz.data.remote.model

import com.google.gson.annotations.SerializedName

data class CharacterDetailDta(
    @SerializedName("name")
    var nombre: String,
    @SerializedName("photoUrl")
    var foto: String,
    @SerializedName("affiliation")
    var afiliacion: String,
    @SerializedName("gender")
    var genero: String,
    @SerializedName("profession")
    var profesion: String,
    @SerializedName("position")
    var posicion: String,
    @SerializedName("weapon")
    var elemento: String
)
