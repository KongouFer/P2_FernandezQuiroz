package com.example.p2_fernandezquiroz.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.p2_fernandezquiroz.data.remote.model.CharacterDta
import com.example.p2_fernandezquiroz.data.remote.model.CharactersAPI
import com.example.p2_fernandezquiroz.databinding.ActivityMainBinding
import com.example.p2_fernandezquiroz.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val charactersAPI = retrofit.create(CharactersAPI::class.java)
        val call: Call<List<CharacterDta>> = charactersAPI.getCharacters("https://last-airbender-api.fly.dev/api/v1/characters?perPage=497")

        call.enqueue(object: Callback<List<CharacterDta>>{
            override fun onResponse(
                p0: Call<List<CharacterDta>>,
                response: Response<List<CharacterDta>>
            ) {
                binding.pbLoading.visibility = View.INVISIBLE
                Log.d(Constants.LOGTAG, "Respuesta recibida: ${response.body()}")
                response.body()?.let{
                    personajes -> val miAdapter = CharAdapter(personajes){
                    personaje -> personaje.id?.let{
                        id ->
                        val bundle = bundleOf(
                            "_id" to id
                        )
                    val intent = Intent(this@MainActivity, Detalles::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)
                    }
                }
                    binding.rvPersonajes.apply{
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = miAdapter
                    }
                }
            }
            override fun onFailure(p0: Call<List<CharacterDta>>, p1: Throwable) {
                binding.pbLoading.visibility = View.INVISIBLE
                Toast.makeText(this@MainActivity,
                    "No hay conexión",
                    Toast.LENGTH_SHORT).show()
            }

        })
    }
}
