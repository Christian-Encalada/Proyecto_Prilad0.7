package com.chris.proyecto_prilad


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

private var db = FirebaseFirestore.getInstance()



class NotasActivity : AppCompatActivity() {
    private var selectedDate: String = ""
    lateinit var calendarView: CalendarView
    lateinit var inputMateria: EditText
    lateinit var inputTareas: EditText




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        //pasar a las notas
        val btn: Button = findViewById(R.id.btn_Entrar)
        btn.setOnClickListener {
            val intent: Intent = Intent(this, ListaNotas:: class.java)
            startActivity(intent)
        }

        // calendario test


        val btnAgregarNota = findViewById<Button>(R.id.btn_guardarNota)
        val calendarView = findViewById<CalendarView>(R.id.calendarView2)
        val fechaTextView = findViewById<TextView>(R.id.id_Fecha)


        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month+1}/$year"
            fechaTextView.text = selectedDate
        }

        btnAgregarNota.setOnClickListener {
            inputMateria = findViewById(R.id.id_input_materia)
            inputTareas = findViewById(R.id.input_tareas)

            val materia = inputMateria.text.toString().trim()
            val tareas = inputTareas.text.toString().trim()

            if (materia.isNotEmpty() && tareas.isNotEmpty()) {
                val nota = hashMapOf(
                    "fecha" to selectedDate,
                    "materia" to materia,
                    "tareas" to tareas
                )

                db.collection("notas")
                    .add(nota)
                    .addOnSuccessListener {
                        // Nota agregada con éxito
                    }
                    .addOnFailureListener { exception ->
                        // Error al agregar la nota
                        Log.w("Error", "Error al agregar la nota", exception)
                    }
            } else {
                // Faltan campos por llenar
                Toast.makeText(this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }




    }

