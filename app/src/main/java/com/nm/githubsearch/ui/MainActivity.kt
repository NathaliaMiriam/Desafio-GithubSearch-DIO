package com.nm.githubsearch.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nm.githubsearch.R
import com.nm.githubsearch.data.GitHubService
import com.nm.githubsearch.domain.Repository
import com.nm.githubsearch.ui.adapter.RepositoryAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var nomeUsuario: EditText
    lateinit var btnConfirmar: Button
    lateinit var listaRepositorios: RecyclerView
    lateinit var githubApi: GitHubService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupListeners()
        showUserName()
        setupRetrofit()
        getAllReposByUserName()
    }

    // Metodo responsavel por realizar o setup da view e recuperar os ids do layout
    private fun setupView() {

        // @TODO 1 - recuperar os id's da tela para a Activity com o findViewById
        nomeUsuario = findViewById(R.id.et_nome_usuario)
        btnConfirmar = findViewById(R.id.btn_confirmar)
    }

    // Metodo responsavel por configurar os listeners click da tela
    private fun setupListeners() {

        // @TODO 2 - colocar a açao de click do botao confirmar
        btnConfirmar.setOnClickListener {
            nomeUsuario.text
            saveUserLocal()
        }
    }

    // Salva o usuario preenchido no EditText utilizando uma SharedPreferences
    private fun saveUserLocal() {

        // @TODO 3 - persistir o usuario preenchido na EditText com a SharedPref no listener do botao salvar
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        val userName = nomeUsuario.text.toString()
        with(sharedPref.edit()) {
            putString(getString(R.string.saved_user_local), userName)
            apply()
        }
    }

    private fun showUserName() {

        // @TODO 4 - depois de persistir o usuario exibir sempre as informacoes no EditText se a sharedpref possuir algum valor, exibir no proprio EditText o valor salvo
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val savedUser = sharedPref.getString(getString(R.string.saved_user_local), "")
        nomeUsuario.setText(savedUser)
    }

    // Metodo responsavel por fazer a configuracao base do Retrofit
    private fun setupRetrofit() {
        /*
           @TODO 5 - realizar a configuracao base do retrofit
           Documentacao oficial do retrofit - https://square.github.io/retrofit/
           URL_BASE da API do  GitHub= https://api.github.com/
           Lembrar de utilizar o GsonConverterFactory mostrado no curso
        */

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        githubApi = retrofit.create(GitHubService::class.java)
    }

    // Metodo responsavel por buscar todos os repositorios do usuario fornecido
    private fun getAllReposByUserName() {

        // @TODO 6 - realizar a implementacao do callback do retrofit e chamar o metodo setupAdapter se retornar os dados com sucesso
        githubApi.getAllRepositoriesByUser(nomeUsuario.text.toString()).enqueue(object : Callback<List<Repository>> {
            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                if (response.isSuccessful) {
                    val repos = response.body()
                    setupAdapter(repos ?: emptyList())
                } else {
                    // Tratar erro aqui, se necessário
                }
            }

            override fun onFailure(call: Call<List<Repository>, t: Throwable) {
                // Tratar erro aqui, se necessário
            }
        })
    }

    // Metodo responsavel por realizar a configuracao do adapter
    fun setupAdapter(list: List<Repository>) {

        // @TODO 7 - implementar a configuracao do Adapter, construir o adapter e instancia-lo passando a listagem dos repositorios
        val adapter = RepositoryAdapter(list)
        val layoutManager = LinearLayoutManager(this)

        listaRepositorios.layoutManager = layoutManager
        listaRepositorios.adapter = adapter
    }

    // Metodo responsavel por compartilhar o link do repositorio selecionado

    // @Todo 11 - colocar esse metodo no click do share item do adapter
    fun shareRepositoryLink(urlRepository: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, urlRepository)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    // Metodo responsavel por abrir o browser com o link informado do repositorio

    // @Todo 12 - colocar esse metodo no click item do adapter
    fun openBrowser(urlRepository: String) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(urlRepository)
            )
        )

    }

}
