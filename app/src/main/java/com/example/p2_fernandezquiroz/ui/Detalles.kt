package com.example.p2_fernandezquiroz.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.p2_fernandezquiroz.R
import com.example.p2_fernandezquiroz.data.remote.model.CharactersAPI
import com.example.p2_fernandezquiroz.data.remote.model.CharacterDetailDta
import com.example.p2_fernandezquiroz.data.remote.model.CharacterDta
import com.example.p2_fernandezquiroz.databinding.ActivityDetallesBinding
import com.example.p2_fernandezquiroz.util.Constants
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Detalles : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        val id = bundle?.getString("id", "")

        Log.d(Constants.LOGTAG, "Id recibido $id")

        //Generamos una instancia a retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val charApi = retrofit.create(CharactersAPI::class.java)

        //val call: Call<GameDetailDto> = gamesApi.getGameDetail(id!!)

        //Para Apiary
        val call: Call<CharacterDetailDta> = charApi.getCharacterDetail2(id!!)

        call.enqueue(object: Callback<CharacterDetailDta>{
            override fun onResponse(p0: Call<CharacterDetailDta>, response: Response<CharacterDetailDta>) {
                binding.pbLoading.visibility = View.INVISIBLE
                binding.apply {


                    tvNameDetalle.text = response.body()?.nombre
                    tvGeneroDetalle.text = response.body()?.genero
                    tvFaccionDetalle.text = response.body()?.afiliacion
                    tvPosicionDetalle.text = response.body()?.posicion
                    tvProfesionDetalle.text = response.body()?.profesion
                    tvElementoDetalle.text = response.body()?.elemento

                    Glide.with(this@Detalles)
                        .load(response.body()?.foto)
                        .into(ivImage)
                }



            }

            override fun onFailure(p0: Call<CharacterDetailDta>, p1: Throwable) {
                //Manejamos el error de conexión
                binding.pbLoading.visibility = View.INVISIBLE
            }

        })


    }
}