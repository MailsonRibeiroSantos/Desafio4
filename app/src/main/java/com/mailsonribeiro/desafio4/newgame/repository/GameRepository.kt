package com.mailsonribeiro.desafio4.newgame.repository

import com.google.firebase.database.DatabaseReference
import com.mailsonribeiro.desafio4.newgame.model.GameModel

class GameRepository {
    suspend fun saveGame( gameModel: GameModel,ref: DatabaseReference,userId:String) {
        val game = ref.child(userId).child(gameModel.name)
        game.setValue(gameModel)
    }

    suspend fun saveImage(){

    }
}
