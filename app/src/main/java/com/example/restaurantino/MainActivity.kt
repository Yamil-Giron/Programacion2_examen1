package com.example.restaurantino

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pastelPrice = 12000
        val cazuelaPrice = 10000

        val pastelQtyEditText = findViewById<EditText>(R.id.pastelQtyEditText)
        val cazuelaQtyEditText = findViewById<EditText>(R.id.cazuelaQtyEditText)
        val tipSwitch = findViewById<Switch>(R.id.tipSwitch)
        val totalTextView = findViewById<TextView>(R.id.totalTextView)

        val numberFormat = NumberFormat.getCurrencyInstance(Locale("es", "CL"))

        val calculateTotal = {
            val pastelQty = pastelQtyEditText.text.toString().toIntOrNull() ?: 0
            val cazuelaQty = cazuelaQtyEditText.text.toString().toIntOrNull() ?: 0

            var total = (pastelQty * pastelPrice) + (cazuelaQty * cazuelaPrice)

            if (tipSwitch.isChecked) {
                total += (total * 0.1).toInt()
            }

            totalTextView.text = "Total: ${numberFormat.format(total)}"
        }

        pastelQtyEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = calculateTotal()
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        cazuelaQtyEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = calculateTotal()
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        tipSwitch.setOnCheckedChangeListener { _, _ -> calculateTotal() }
    }
}
