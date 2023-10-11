package com.nm.githubsearch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nm.githubsearch.R
import com.nm.githubsearch.domain.Repository

class RepositoryAdapter(private val repositories: List<Repository>) :
    RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    var carItemLister: (Repository) -> Unit = {}
    var btnShareLister: (Repository) -> Unit = {}

    // Cria uma nova view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)
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
    }

    // Pega a quantidade de repositorios da lista
    // @TODO 9 - realizar a contagem da lista
    override fun getItemCount(): Int = 0

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // @TODO 10 - implementar o ViewHolder para os repositorios
        // Exemplo:
        // val atributo: TextView

        // init {
        //    view.apply {
        //        atributo = findViewById(R.id.item_view)
        //    }

    }
}
