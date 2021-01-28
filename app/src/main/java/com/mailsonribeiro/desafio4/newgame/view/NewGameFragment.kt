package com.mailsonribeiro.desafio4.newgame.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.mailsonribeiro.desafio4.R
import com.mailsonribeiro.desafio4.newgame.repository.GameRepository
import com.mailsonribeiro.desafio4.newgame.viewmodel.GameViewModel


class NewGameFragment : Fragment() {
    private  lateinit var _viewModel :GameViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var saveNewGame = view.findViewById<MaterialButton>(R.id.btnSaveNewGame)
        var edtNameNewGame = view.findViewById<TextInputEditText>(R.id.edtNameNewGame)
        var edtCreateAtNewGame = view.findViewById<TextInputEditText>(R.id.edtCreateAtNewGame)
        var edtDescriptionNewGame = view.findViewById<TextInputEditText>(R.id.edtDescriptionNewGame)

        _viewModel =
                ViewModelProvider(this, GameViewModel.GameViewModelFactory(GameRepository())).get(
                        GameViewModel::class.java
                )

        var auth = FirebaseAuth.getInstance()
        var id = auth.currentUser!!.uid

        var database = FirebaseDatabase.getInstance()
        var ref = database.getReference("usuario")

        saveNewGame.setOnClickListener {
            var name = edtNameNewGame.text.toString()
            var date = edtCreateAtNewGame.text.toString()
            var description = edtDescriptionNewGame.text.toString()
            _viewModel.saveGame(name,date,description,"",ref,id).observe(viewLifecycleOwner, {

            })
        }
    }


}