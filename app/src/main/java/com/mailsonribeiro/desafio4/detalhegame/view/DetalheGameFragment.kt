package com.mailsonribeiro.desafio4.detalhegame.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.storage.FirebaseStorage
import com.mailsonribeiro.desafio4.R
import com.mailsonribeiro.desafio4.home.view.HomeFragment
import com.squareup.picasso.Picasso


class DetalheGameFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalhe_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firebase = FirebaseStorage.getInstance()
        val storage = firebase.getReference("uploads")

        val descircao = arguments?.getString(HomeFragment.GAME_DESCRIPTION)
        val titulo = arguments?.getString(HomeFragment.GAME_NAME)
        val ano = arguments?.getString(HomeFragment.GAME_YEAR)
        val imageURL = arguments?.getString(HomeFragment.GAME_IMAGE)

        val txtDescricao = view.findViewById<TextView>(R.id.txtDescriptionDetail)
        val txtTitulo = view.findViewById<TextView>(R.id.txtTitleDetail)
        val txtSubTitulo = view.findViewById<TextView>(R.id.txtSubTitleDetail)
        val txtAno = view.findViewById<TextView>(R.id.txtYearDetail)
        val imvImageURL = view.findViewById<ImageView>(R.id.imvGameDetail)

        txtDescricao.text = descircao
        txtTitulo.text = titulo
        txtSubTitulo.text = titulo
        txtAno.text = "Lançamento: "+ ano
        if (imageURL != null) {
            storage.child(imageURL.substringAfter("uploads/")).downloadUrl.addOnSuccessListener {
                Picasso.get().load(it).into(imvImageURL)
            }
        }

    }

}