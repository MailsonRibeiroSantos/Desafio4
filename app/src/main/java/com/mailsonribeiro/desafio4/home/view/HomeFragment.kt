package com.mailsonribeiro.desafio4.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mailsonribeiro.desafio4.R
import com.mailsonribeiro.desafio4.newgame.model.GameModel


class HomeFragment : Fragment() {

    private lateinit var _listaAdapter: GameAdapter
    private lateinit var _view: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view
        var fbtnSaveGame =  view.findViewById<FloatingActionButton>(R.id.fbtnSaveGame)
        var navController = Navigation.findNavController(view)

        var auth = FirebaseAuth.getInstance()
        var id = auth.currentUser!!.uid
        val games = mutableListOf<GameModel>()

        var database = FirebaseDatabase.getInstance()
        var ref = database.getReference("usuario")

        fbtnSaveGame.setOnClickListener {
            navController.navigate(R.id.newGameFragment)
        }


        ref.child(id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {


                if (dataSnapshot.hasChildren()) {
                    dataSnapshot.children.forEach { snapshotChildren ->
                        val game = snapshotChildren.getValue(GameModel::class.java)
                        game?.let { games.add(it) }
                    }
                }

                addItens(games)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }
    fun addItens(games: MutableList<GameModel>){
            val lista = _view.findViewById<RecyclerView>(R.id.recycleGames)
            val manager = GridLayoutManager(_view.context,2)

            _listaAdapter = GameAdapter(games){

            }

            lista.apply {
                setHasFixedSize(true)

                layoutManager = manager
                adapter = _listaAdapter
            }
        }
}
