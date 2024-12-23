package com.example.restaurantino

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurante.model.Platillo
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var pastelDeChoclo: Platillo
    private lateinit var cazuela: Platillo
    private lateinit var etCantidadPastelDeChoclo: EditText
    private lateinit var etCantidadCazuela: EditText
    private lateinit var swPropina: Switch
    private lateinit var tvSubtotal: TextView
    private lateinit var tvPropina: TextView
    private lateinit var tvTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pastelDeChoclo = Platillo("Pastel de Choclo", 12000)
        cazuela = Platillo("Cazuela", 10000)

        etCantidadPastelDeChoclo = findViewById(R.id.etCantidadPastelDeChoclo)
        etCantidadCazuela = findViewById(R.id.etCantidadCazuela)
        swPropina = findViewById(R.id.swPropina)
        tvSubtotal = findViewById(R.id.tvSubtotal)
        tvPropina = findViewById(R.id.tvPropina)
        tvTotal = findViewById(R.id.tvTotal)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                actualizarMontos()
            }
            override fun afterTextChanged(s: Editable?) {}
        }

        etCantidadPastelDeChoclo.addTextChangedListener(textWatcher)
        etCantidadCazuela.addTextChangedListener(textWatcher)
        swPropina.setOnCheckedChangeListener { _, _ -> actualizarMontos() }
    }

    private fun actualizarMontos() {
        val cantidadPastelDeChoclo = etCantidadPastelDeChoclo.text.toString().toIntOrNull() ?: 0
        val cantidadCazuela = etCantidadCazuela.text.toString().toIntOrNull() ?: 0

        val subtotalPastelDeChoclo = cantidadPastelDeChoclo * pastelDeChoclo.precio
        val subtotalCazuela = cantidadCazuela * cazuela.precio
        val subtotal = subtotalPastelDeChoclo + subtotalCazuela

        val propina = if (swPropina.isChecked) subtotal * 0.1 else 0.0
        val total = subtotal + propina

        val format = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
        tvSubtotal.text = "Subtotal: ${format.format(subtotal)}"
        tvPropina.text = "Propina: ${format.format(propina)}"
        tvTotal.text = "Total a pagar: ${format.format(total)}"
    }
}
