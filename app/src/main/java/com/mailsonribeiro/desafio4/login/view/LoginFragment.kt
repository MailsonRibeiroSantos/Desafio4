package com.mailsonribeiro.desafio4.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
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
            navController.navigate(R.id.homeFragment)
        }
    }


}