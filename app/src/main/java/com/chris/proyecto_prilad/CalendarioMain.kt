package com.chris.proyecto_prilad

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast

class CalendarioMain : AppCompatActivity() {
    lateinit var calendarView: CalendarView
    lateinit var inputMateria: EditText
    lateinit var inputTareas: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendario_main)
        calendarView = findViewById(R.id.calendarioV)
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val builder = AlertDialog.Builder(this)
            val builder3 = AlertDialog.Builder(this)

            builder.setTitle("Ingresa información para la fecha seleccionada")
            builder.setMessage("Fecha seleccionada: $dayOfMonth/$month/$year")
            builder.setMessage("Materia:")
            inputMateria = EditText(this)
            builder.setView(inputMateria)
            builder.setPositiveButton("Aceptar") { dialog, which ->
                val materia = inputMateria.text.toString()
                if (materia.isNotEmpty()) {
// muestra el siguiente dialogo
                    builder3.setTitle("Ingresa información para la fecha seleccionada")
                    builder3.setMessage("Fecha seleccionada: $dayOfMonth/$month/$year")
                    builder3.setMessage("Tareas:")
                    inputTareas = EditText(this)
                    builder3.setView(inputTareas)
                    builder3.setPositiveButton("Aceptar") { dialog, which ->
                        val tareas = inputTareas.text.toString()
                        if (tareas.isNotEmpty()) {
// guarda la información ingresada junto con la fecha seleccionada
// en una base de datos o archivo de preferencias.
                            saveInfo(year, month, dayOfMonth, materia, tareas)
                        } else {
                            Toast.makeText(this, "Por favor ingresa tareas", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    builder3.setNegativeButton("Cancelar") { dialog, which ->
                        dialog.cancel()
                    }
                    builder3.show()
                } else {
                    Toast.makeText(this, "Por favor ingresa materia", Toast.LENGTH_SHORT).show()
                }
            }
            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.cancel()
            }
            builder.show()
        }
    }

    private fun saveInfo(year: Int, month: Int, dayOfMonth: Int, materia: String, tareas: String) {
        // Aquí debes implementar el código para guardar la información
        // ingresada junto con la fecha seleccionada en una base de datos de Firebase
        // Puedes utilizar Firebase Realtime Database, Firestore, etc.
    }
}
