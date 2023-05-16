package com.thanebuhanan.caloriecounter.ui

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter

@BindingAdapter("android:text")
fun bindIntegerInText(tv: EditText, value: Int) {
    if (tv.text.toString() != value.toString())
        tv.setText(value.toString())
}

@InverseBindingAdapter(attribute = "android:text")
fun getIntegerFromBinding(view: TextView): Int {
    var str = view.text.toString() // "".toInt()
    if (str == "") {
        str = "0"
    }
    return str.toInt()
}
