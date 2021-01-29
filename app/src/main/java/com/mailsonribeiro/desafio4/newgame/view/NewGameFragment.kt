package com.mailsonribeiro.desafio4.newgame.view

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.mailsonribeiro.desafio4.R
import com.mailsonribeiro.desafio4.newgame.repository.GameRepository
import com.mailsonribeiro.desafio4.newgame.viewmodel.GameViewModel
import java.lang.System.currentTimeMillis


class NewGameFragment : Fragment() {
    private  lateinit var _viewModel :GameViewModel
    private  lateinit var _view :View
    private var imageURI: Uri? = null
    private var _fileReference: String =""


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
        var ivGameNewGame = view.findViewById<ImageView>(R.id.ivGameNewGame)

        _view = view
        _viewModel =
                ViewModelProvider(this, GameViewModel.GameViewModelFactory(GameRepository())).get(
                        GameViewModel::class.java
                )



        ivGameNewGame.setOnClickListener {
            procurarArquivo()
        }

        saveNewGame.setOnClickListener {
            saveGame()

        }
    }
    fun procurarArquivo() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, CONTENT_REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CONTENT_REQUEST_CODE && resultCode == RESULT_OK) {
            imageURI = data?.data
            _view.findViewById<ImageView>(R.id.ivGameNewGame).setImageURI(imageURI)
        }
    }
    fun saveGame() {
        var auth = FirebaseAuth.getInstance()
        var id = auth.currentUser!!.uid

        var database = FirebaseDatabase.getInstance()
        var ref = database.getReference("usuario")

        var edtNameNewGame = _view.findViewById<TextInputEditText>(R.id.edtNameNewGame)
        var edtCreateAtNewGame = _view.findViewById<TextInputEditText>(R.id.edtCreateAtNewGame)
        var edtDescriptionNewGame = _view.findViewById<TextInputEditText>(R.id.edtDescriptionNewGame)
        var navController = Navigation.findNavController(_view)

        imageURI?.run {
            val firebase = FirebaseStorage.getInstance()
            val storage = firebase.getReference("uploads")

            val extension = MimeTypeMap.getSingleton()
                .getExtensionFromMimeType(requireActivity().contentResolver.getType(imageURI!!))

            val fileReference = storage.child("${currentTimeMillis()}.${extension}")

            fileReference.putFile(this)
                .addOnSuccessListener {
                    _fileReference = fileReference.toString()
                    var name = edtNameNewGame.text.toString()
                    var date = edtCreateAtNewGame.text.toString()
                    var description = edtDescriptionNewGame.text.toString()
                    _viewModel.saveGame(name,date,description,_fileReference,ref,id).observe(viewLifecycleOwner, {
                        navController.popBackStack()
                    })


                }
                .addOnFailureListener {
                    Toast.makeText(
                        _view.context,
                        "Falha ao carregar imagem!!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
        }



    }




    companion object {
        const val CONTENT_REQUEST_CODE = 1
    }


}