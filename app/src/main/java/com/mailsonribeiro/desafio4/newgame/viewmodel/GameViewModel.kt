package com.mailsonribeiro.desafio4.newgame.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.DatabaseReference
import com.mailsonribeiro.desafio4.newgame.model.GameModel
import com.mailsonribeiro.desafio4.newgame.repository.GameRepository
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class GameViewModel(private val repository: GameRepository): ViewModel() {
    fun saveGame(name: String, date: String, description: String, imgURL: String, ref: DatabaseReference,userId: String) = liveData(Dispatchers.IO){
        repository.saveGame(GameModel(imgURL,name, date, description),ref,userId)
        emit(true)
    }

    class GameViewModelFactory(private val repository: GameRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GameViewModel(repository) as T
        }
    }
}