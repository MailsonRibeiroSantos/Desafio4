package com.mailsonribeiro.desafio4.createaccount.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mailsonribeiro.desafio4.R



class CreateAccountFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var btnCreateAccount = view.findViewById<MaterialButton>(R.id.btnCreateAccount)
        var navController = Navigation.findNavController(view)
        auth = Firebase.auth
        btnCreateAccount.setOnClickListener {
            val nome = view.findViewById<TextInputEditText>(R.id.edtNameCreateaccount).text.toString()
            val email = view.findViewById<TextInputEditText>(R.id.edtEmailCreateaccount).text.toString()
            val password = view.findViewById<TextInputEditText>(R.id.edtPasswordCreateaccount).text.toString()
            val repeatPassword = view.findViewById<TextInputEditText>(R.id.edtRepeatPasswordCreateaccount).text.toString()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{task ->
                    if(task.isSuccessful) {
                        Toast.makeText(view.context, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()

                        navController.navigate(R.id.loginFragment)
                    } else {
                        Toast.makeText(view.context, "Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show()
                    }
                }
            }

    }
}
