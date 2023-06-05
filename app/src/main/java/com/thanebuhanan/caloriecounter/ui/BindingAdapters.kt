package com.thanebuhanan.caloriecounter.ui

import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.thanebuhanan.caloriecounter.R

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

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}