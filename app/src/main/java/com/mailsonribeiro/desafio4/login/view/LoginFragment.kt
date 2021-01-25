package com.mailsonribeiro.desafio4.login.view

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
import com.mailsonribeiro.desafio4.R

class LoginFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var navController = Navigation.findNavController(view)
        view.findViewById<MaterialButton>(R.id.btnRegisterLogin).setOnClickListener{
            navController.navigate(R.id.createAccountFragment)
        }
        view.findViewById<MaterialButton>(R.id.btnLoginLogin).setOnClickListener{

            val edtEmail = view.findViewById<TextInputEditText>(R.id.edtEmailLogin)
            val edtSenha = view.findViewById<TextInputEditText>(R.id.edtPasswordLogin)

            val email = edtEmail.text.toString().trim()
            val password = edtSenha.text.toString().trim()

            when {
                email.isEmpty() -> {
                    Toast.makeText(view.context, "Preencha o campo de email!", Toast.LENGTH_SHORT).show()
                }
                password.isEmpty() -> {
                    Toast.makeText(view.context, "Preencha o campo de senha!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener{
                            if(it.isSuccessful) {
                                navController.navigate(R.id.homeFragment)

                            } else {
                                Toast.makeText(view.context, "Email ou senha incorretas!", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }

        }
    }


}