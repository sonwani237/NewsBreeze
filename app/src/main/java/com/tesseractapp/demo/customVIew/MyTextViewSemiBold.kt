package com.tesseractapp.demo.customVIew

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.tesseractapp.demo.custom.CustomFont

class MyTextViewSemiBold : AppCompatTextView {
    constructor(context: Context): super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr){
        init()
    }

    fun init() {
        val tf = CustomFont.setFontSemiBold(context.assets)
        typeface = tf

    }

    override fun getAutofillType(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            View.AUTOFILL_TYPE_NONE
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }
}