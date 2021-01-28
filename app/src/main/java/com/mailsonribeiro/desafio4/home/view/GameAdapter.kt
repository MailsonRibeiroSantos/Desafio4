package com.mailsonribeiro.desafio4.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mailsonribeiro.desafio4.R
import com.mailsonribeiro.desafio4.newgame.model.GameModel

class GameAdapter (private val games: List<GameModel>, private val listener: (GameModel) -> Unit):
        RecyclerView.Adapter<GameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)

        return GameViewHolder(view)
    }
    override fun getItemCount() = games.size

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(games[position])
        holder.itemView.setOnClickListener { listener(games[position]) }
    }

}