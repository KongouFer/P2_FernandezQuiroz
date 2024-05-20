package com.example.p2_fernandezquiroz.data.remote.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface CharactersAPI {
    @GET
    fun getCharacters(
        @Url url: String
    ): Call<List<CharacterDta>>
    //https://last-airbender-api.fly.dev/api/v1/characters/5cf5679a915ecad153ab68cc

    @GET("api/v1/characters")
    fun getCharacterDetail(
        @Query("_id") id: String
    ): Call<CharacterDetailDta>

    @GET("api/v1/characters/{id}")
    fun getCharacterDetail2(
        @Path("_id") id: String

    ): Call<CharacterDetailDta>
}