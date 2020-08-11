package com.violet.openweather.extension

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.facebook.drawee.view.SimpleDraweeView


fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun AppCompatEditText.clearInputText(){
    setText("")
}

fun Double.weatherValue(): String{
    return this.toInt().toString()
}

@BindingAdapter("imageUrl")
fun setImageUrl(view: SimpleDraweeView, iconName: String?) {
    iconName?.let {
        view.setImageURI("https://openweathermap.org/img/wn/$iconName@2x.png")
    }
}