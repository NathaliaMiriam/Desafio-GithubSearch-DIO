package com.nm.githubsearch.ui.adapter

import android.content.Intent
import android.net.Uri
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nm.githubsearch.R
import com.nm.githubsearch.domain.Repository

class RepositoryAdapter(private val repositories: List<Repository>) :
    RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    var repoItemLister: (Repository) -> Unit = {}
    var btnShareLister: (Repository) -> Unit = {}

    // Cria uma nova view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)
        return ViewHolder(view)
    }

    // Pega o conteudo da view e troca pela informacao de item de uma lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // @TODO 8 - realizar o bind do ViewHolder
        // Exemplo de Bind
        // holder.preço.text = repositories[position].atributo

        // Exemplo de click no item
        // holder.itemView.setOnClickListener {
        //   carItemLister(repositores[position])
        // }

        // Exemplo de click no btn Share
        // holder.favorito.setOnClickListener {
        //   btnShareLister(repositores[position])
        // }

        val repository = repositories[position]
        holder.bind(repository)
    }

    // Pega a quantidade de repositorios da lista

    // @TODO 9 - realizar a contagem da lista
    override fun getItemCount(): Int = repositories.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // @TODO 10 - implementar o ViewHolder para os repositorios
        // Exemplo:
        // val atributo: TextView

        // init {
        //    view.apply {
        //        atributo = findViewById(R.id.item_view)
        //    }

        private val nomeRepositorio: TextView
        init {
            nomeRepositorio = view.findViewById(R.id.tv_nome_repo)
        }


        fun bind(repository: Repository) {
            // Exibe o nome do repositório no TextView de título
            nomeRepositorio.text = repository.name
        }

    }
}
