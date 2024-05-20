package com.example.p2_fernandezquiroz.data.remote.model

import com.google.gson.annotations.SerializedName

data class CharacterDta(
    @SerializedName("_id")
    var id: String,
    @SerializedName("photoUrl")//
    var imagen: String,
    @SerializedName("name")
    var nombre: String,
    @SerializedName("affiliation")
    var bando: String
)
