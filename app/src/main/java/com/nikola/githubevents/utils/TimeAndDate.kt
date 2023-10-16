package com.nikola.githubevents.utils

import java.text.SimpleDateFormat

fun parseDate(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val outputFormat = SimpleDateFormat("MM/dd/yyyy hh:mm:ss")

    return try {
        val date = inputFormat.parse(dateString)
        outputFormat.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        "" // Handle parsing error
    }
}