package com.example.searchstudy.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}


fun String.toLanguageString(): String {
    when (this) {
        "ko" -> { return "한국어" }
        "ja" -> { return "일본어" }
        "zh-CN" -> { return "중국어 간체" }
        "zh-TW" -> { return "중국어 번체" }
        "hi" -> { return "힌디어" }
        "en" -> { return "영어" }
        "es" -> { return "스페인어" }
        "fr" -> {return "프랑스어"}
        "de" -> {return "독일어"}
        "pt" -> {return "포르투갈어"}
        "vi" -> {return "베트남어"}
        "id" -> {return "인도네시아어"}
        "fa" -> {return "페르시아어"}
        "ar" -> {return "아랍어"}
        "mm" -> {return "미얀마어"}
        "th" -> {return "태국어"}
        "ru" -> {return "러시아어"}
        "it" -> {return "이탈리아어"}
        else -> {return "알 수 없음"}
    }
}
