package com.alvayonara.jetpack_submission_alvayonara

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateConvert {
    fun convertDate(date: String?): String? {
        // default format date
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // assign empty value for convertResult
        var convertResult = ""

        try {
            val dateCurrentFormat = "$date"

            val dateFormat = formatter.parse(dateCurrentFormat)

            // reformat date time style (ex: December 31, 1997)
            val formatterReformat =
                SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())

            // convert result
            convertResult = formatterReformat.format(dateFormat!!)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return convertResult
    }
}