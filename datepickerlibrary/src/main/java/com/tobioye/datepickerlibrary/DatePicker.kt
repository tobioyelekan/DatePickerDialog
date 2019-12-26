package com.tobioye.datepickerlibrary

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class DatePicker(
    private var day: Int?,
    private var month: Int?,
    private var year: Int?
) : DialogFragment() {

    private var dateBackgroundColor = R.color.blue_bg
    private var dateTextColor = android.R.color.white

    private var listener: DatePickerDialog.OnDateSetListener? = null

    fun setListener(listener: DatePickerDialog.OnDateSetListener) {
        this.listener = listener
    }

    private lateinit var dateText: TextView

    private val realMonths = listOf(
        "Jan" to "January", "Feb" to "February", "Mar" to "March", "Apr" to "April", "May" to "May",
        "Jun" to "June", "Jul" to "July", "Aug" to "August", "Sep" to "September",
        "Oct" to "October", "Nov" to "November", "Dec" to "December"
    )

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView = View.inflate(requireContext(), R.layout.date_picker_dialog, null)

        val dayPicker = dialogView.findViewById(R.id.picker_day) as NumberPicker
        val monthPicker = dialogView.findViewById(R.id.picker_month) as NumberPicker
        val yearPicker = dialogView.findViewById(R.id.picker_year) as NumberPicker
        dateText = dialogView.findViewById(R.id.date_text) as TextView

        dateText.setBackgroundColor(ContextCompat.getColor(requireContext(), dateBackgroundColor))
        dateText.setTextColor(ContextCompat.getColor(requireContext(), dateTextColor))

        val months = arrayOf(
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
            "Aug",
            "Sep",
            "Oct",
            "Nov",
            "Dec"
        )
        val cal = Calendar.getInstance()

        dayPicker.minValue = MIN_DAY
        dayPicker.maxValue = MAX_DAY
        dayPicker.value = day ?: cal[Calendar.DAY_OF_MONTH]
        day = dayPicker.value

        monthPicker.minValue = 0
        monthPicker.maxValue = MAX_MONTH
        monthPicker.displayedValues = months
        monthPicker.value = month ?: cal[Calendar.MONTH]
        month = monthPicker.value

        val maxYear = year ?: cal[Calendar.YEAR]
        yearPicker.minValue = MIN_YEAR
        yearPicker.maxValue = maxYear
        yearPicker.value = maxYear
        year = yearPicker.value

        displayDate()

        dayPicker.setOnValueChangedListener { _, _, day ->
            this.day = day
            displayDate()
        }
        monthPicker.setOnValueChangedListener { _, _, month ->
            this.month = month
            displayDate()
        }
        yearPicker.setOnValueChangedListener { _, _, year ->
            this.year = year
            displayDate()
        }

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)
            .setPositiveButton(getString(R.string.dialog_ok)) { _, _ ->
                listener!!.onDateSet(
                    null,
                    dayPicker.value,
                    monthPicker.value,
                    yearPicker.value
                )
            }
            .setNegativeButton(getString(R.string.dialog_cancel)) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }

        return builder.create()
    }

    fun setDateBackgroundColor(@ColorRes color: Int) {
        dateBackgroundColor = color
    }

    private fun displayDate() {
        val day = day!!
        val month = month!!
        val year = year!!

        val realMonth = realMonths[month].second
        dateText.text = getString(R.string.date, realMonth, day, year)
    }

    companion object {
        private const val MIN_DAY = 1
        private const val MAX_MONTH = 11
        private const val MAX_DAY = 31
        private const val MIN_YEAR = 1900
    }
}
