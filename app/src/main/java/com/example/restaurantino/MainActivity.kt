package com.example.restaurantino

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextCantidadPastel = findViewById<EditText>(R.id.editTextCantidadPastel)
        val editTextCantidadCazuela = findViewById<EditText>(R.id.editTextCantidadCazuela)
        val checkBoxPropina = findViewById<CheckBox>(R.id.checkBoxPropina)
        val buttonCalcular = findViewById<Button>(R.id.buttonCalcular)
        val textViewResumen = findViewById<TextView>(R.id.textViewResumen)
        val textViewTotal = findViewById<TextView>(R.id.textViewTotal)

        buttonCalcular.setOnClickListener {
            val cantidadPastel = editTextCantidadPastel.text.toString().toIntOrNull() ?: 0
            val cantidadCazuela = editTextCantidadCazuela.text.toString().toIntOrNull() ?: 0

            val precioPastel = 12000
            val precioCazuela = 10000

            val totalPastel = cantidadPastel * precioPastel
            val totalCazuela = cantidadCazuela * precioCazuela
            var total = totalPastel + totalCazuela

            if (checkBoxPropina.isChecked) {
                total += (total * 0.1).toInt()
            }

            val resumen = StringBuilder()
            if (cantidadPastel > 0) {
                resumen.append("Pastel de Choclo: $cantidadPastel x $$precioPastel = $$totalPastel\n")
            }
            if (cantidadCazuela > 0) {
                resumen.append("Cazuela: $cantidadCazuela x $$precioCazuela = $$totalCazuela\n")
            }

            textViewResumen.text = resumen.toString()
            textViewTotal.text = "Total a pagar: $$total"
        }
    }
}
