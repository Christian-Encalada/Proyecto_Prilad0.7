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
    lateinit var inputProducto: EditText
    lateinit var inputTareas: EditText




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)



        // calendario test


        val btnAgregarNota = findViewById<Button>(R.id.btn_guardarNota)
        val calendarView = findViewById<CalendarView>(R.id.calendarView2)
        val fechaTextView = findViewById<TextView>(R.id.id_Fecha)


        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month+1}/$year"
            fechaTextView.text = selectedDate
        }

        btnAgregarNota.setOnClickListener {
            inputProducto = findViewById(R.id.input_producto)
            inputTareas = findViewById(R.id.input_tareas)

            val producto = inputProducto.text.toString().trim()
            val tareas = inputTareas.text.toString().trim()

            if (producto.isNotEmpty() && tareas.isNotEmpty()) {
                val nota = hashMapOf(
                    "fecha" to selectedDate,
                    "producto" to producto,
                    "tareas" to tareas
                )

                db.collection("notas")
                    .add(nota)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Nota guardada correctamente", Toast.LENGTH_SHORT).show()


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

