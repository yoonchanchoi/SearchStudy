package com.example.searchstudy.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.toDateString() : String {
    val format = SimpleDateFormat("HH:mm:ss")
    return format.format(this)
}
