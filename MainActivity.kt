package com.example.ageinminutescalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var selectDate : EditText
    lateinit var tv_selectedDate : TextView
    lateinit var tv_showInMinutes : TextView
    var selectedDate : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectDate = findViewById(R.id.et_selectedDate)
        tv_selectedDate = findViewById(R.id.tv_selectedDate)
        tv_showInMinutes = findViewById(R.id.tv_showInMinutes)

        selectDate.setOnClickListener {
            getDatePicker()
        }

    }

    private fun getDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, { view, year, month, dayOfMonth ->
            tv_selectedDate.text = "$dayOfMonth/${month+1}/$year"
            selectedDate = "$dayOfMonth/${month+1}/$year"

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)
            val selectedDateInMinutes = theDate.time / 60000

            System.out.println(selectedDateInMinutes)

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate.time / 60000

            System.out.println(currentDateInMinutes)

            val diffInMinutes = currentDateInMinutes - selectedDateInMinutes
            tv_showInMinutes.text = diffInMinutes.toString()
            System.out.println(diffInMinutes)

        },year, month, dayOfMonth)
        dpd.show()
    }

}