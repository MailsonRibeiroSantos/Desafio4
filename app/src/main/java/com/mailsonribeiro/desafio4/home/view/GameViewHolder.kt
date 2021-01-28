package com.mailsonribeiro.desafio4.home.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.mailsonribeiro.desafio4.R
import com.mailsonribeiro.desafio4.newgame.model.GameModel
import com.squareup.picasso.Picasso

class GameViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val firebase = FirebaseStorage.getInstance()
        val storage = firebase.getReference("uploads")

        val image = view.findViewById<ImageView>(R.id.ivGameHome)
        val name = view.findViewById<TextView>(R.id.textGameHome)
        val date = view.findViewById<TextView>(R.id.textYearGameHome)

        fun bind(gameModel: GameModel) {
                name.text = gameModel.name
                date.text = gameModel.createAt

                storage.child(gameModel.imageURL.substringAfter("uploads/")).downloadUrl.addOnSuccessListener {
                        Picasso.get().load(it).into(image)
                }
        }

}

