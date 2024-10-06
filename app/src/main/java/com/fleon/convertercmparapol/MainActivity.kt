package com.fleon.convertercmparapol

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val button: Button by lazy { findViewById(R.id.button) }
    private val radioGroup: RadioGroup by lazy { findViewById(R.id.radioGroup) }
    private val input: EditText by lazy {findViewById(R.id.input)}  //botão de converter
    private val conversionOutput: TextView by lazy {findViewById(R.id.conversionOutput)}


    private var centimeterSelected: Boolean = false
    private var inchSelected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupView()
    }

    private fun setupView() {
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if(checkedId == R.id.cmRadioButton){
                centimeterSelected = true
                inchSelected = false
            } else {
                centimeterSelected = false
                inchSelected = true
            }
        }

        button.setOnClickListener{
            val entrada = input.text.toString()
            //val input = findViewById<EditText>(R.id.input)
            if (centimeterSelected){
                conversionOutput.text = getString(R.string.cm_to_inch_output, entrada, convertCmToInches(entrada.toDouble()).toString())
            } else {
                conversionOutput.text = getString(R.string.inch_to_cm_output, entrada, convertInchesToCm(entrada.toDouble()).toString())
            }

        }
    }

    private fun convertCmToInches(value: Double): Double {
        return (value / 2.54)
    }

    private fun convertInchesToCm(value: Double): Double {
        return value * 2.54
    }
}