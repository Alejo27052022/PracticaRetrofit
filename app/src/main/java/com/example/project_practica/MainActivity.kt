package com.example.project_practica

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_practica.data.Datos
import com.example.project_practica.service.RetrofitInstance
import com.example.project_practica.ui.theme.Project_PracticaTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private val retrofitService by lazy {
        RetrofitInstance.getRetrofitService()
    }

    private lateinit var recyclerView: RecyclerView
    private val characters = mutableListOf<Datos>()
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_principal)

        // Configuracion de RecyclerView
        recyclerView = findViewById(R.id.recycler_view)
        characterAdapter = CharacterAdapter(characters)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = characterAdapter

        //Llamada de API
        fetchCharacters()
    }

    private fun fetchCharacters(){
        // Corrutina
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = retrofitService.getMorty()
                withContext(Dispatchers.Main){
                    characters.clear()
                    characters.addAll(response.results)
                    characterAdapter.notifyDataSetChanged()
                }
            }catch (e: Exception){
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        "Error al cargar los datos: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
