package com.example.p2_fernandezquiroz.ui

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.p2_fernandezquiroz.R
import com.example.p2_fernandezquiroz.data.remote.model.CharacterDta
import com.example.p2_fernandezquiroz.data.remote.model.CharactersAPI
import com.example.p2_fernandezquiroz.databinding.ActivityMainBinding
import com.example.p2_fernandezquiroz.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var networkChangeReceiver: NetworkChangeReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        networkChangeReceiver = NetworkChangeReceiver().apply {
            onNetworkChange = {

                val logging =
                    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
                val client = OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.URL_BASE)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val charactersAPI = retrofit.create(CharactersAPI::class.java)
                val call: Call<List<CharacterDta>> =
                    charactersAPI.getCharacters("api/v1/characters?perPage=497")
                //val call: Call<List<CharacterDta>> = charactersAPI.getCharacters("https://www.serverbpw.com/cm/games/games_list.php")
                call.enqueue(object : Callback<List<CharacterDta>> {
                    override fun onResponse(
                        p0: Call<List<CharacterDta>>,
                        response: Response<List<CharacterDta>>
                    ) {
                        binding.pbLoading.visibility = View.INVISIBLE
                        Log.d(Constants.LOGTAG, "Respuesta recibida: ${response.body()}")
                        response.body()?.let { personajes ->
                            val miAdapter = CharAdapter(personajes) { personaje ->
                                personaje.id?.let { id ->
                                    val bundle = bundleOf(
                                        "id" to id
                                    )
                                    val intent = Intent(this@MainActivity, Detalles::class.java)
                                    intent.putExtras(bundle)
                                    startActivity(intent)
                                }
                            }
                            binding.rvPersonajes.apply {
                                layoutManager = LinearLayoutManager(this@MainActivity)
                                adapter = miAdapter
                            }
                        }
                    }

                    override fun onFailure(p0: Call<List<CharacterDta>>, p1: Throwable) {
                        binding.pbLoading.visibility = View.INVISIBLE
                        Toast.makeText(
                            this@MainActivity,
                            R.string.Toast_Sin_Conexion,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            }
        }
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, filter)
    }
}
