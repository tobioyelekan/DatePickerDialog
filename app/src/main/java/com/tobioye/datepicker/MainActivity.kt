package com.tobioye.datepicker

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.tobioye.datepickerlibrary.DatePicker

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showDatePicker(view: View) {
        val datePicker = DatePicker(null, null, null)
        datePicker.setDateBackgroundColor(android.R.color.black)

        datePicker.setListener(DatePickerDialog.OnDateSetListener { _, day, month, year ->
            val date = (getString(R.string.format_date, year, month.inc(), day))
            Toast.makeText(this, date, Toast.LENGTH_LONG).show()
        })

        datePicker.show(supportFragmentManager, "")
    }
}
