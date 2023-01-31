package com.chris.proyecto_prilad

import android.util.Log;
import NotasAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import com.chris.proyecto_prilad.model.Notas
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore


class ListaNotas : AppCompatActivity() {
    private lateinit var mRecycler: RecyclerView
    private lateinit var mAdapter: NotasAdapter
    private lateinit var mFirestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_notas)


        mFirestore = FirebaseFirestore.getInstance()
        mRecycler = findViewById(R.id.recyclerViewSingle)
        mRecycler.layoutManager = LinearLayoutManager(this)
        val query = mFirestore.collection("Notas")

        val firestoreRecyclerOptions =
            FirestoreRecyclerOptions.Builder<Notas>().setQuery(query, Notas::class.java).build()

        mAdapter = NotasAdapter(firestoreRecyclerOptions)
        mAdapter.notifyDataSetChanged()
        Log.d("ListaNotas", "Datos obtenidos: ${mAdapter.itemCount}")
        mRecycler.adapter = mAdapter
        Log.d("ListaNotas", "Adapter asignado al RecyclerView")
    }

    override fun onStart() {
        super.onStart()
        mAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        mAdapter.stopListening()
    }
}
