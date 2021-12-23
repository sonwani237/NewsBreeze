package com.tesseractapp.demo.customVIew

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import com.tesseractapp.demo.R
import com.tesseractapp.demo.custom.CustomFont
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class MyTextInputLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : TextInputLayout(context, attrs, defStyleAttr) {
    private var collapsingTextHelper: Any? = null
    private var bounds: Rect? = null
    private var recalculateMethod: Method? = null

    init {
        init()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        adjustBounds()
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun init() {

        try {
            val cthField = TextInputLayout::class.java.getDeclaredField("mCollapsingTextHelper")
            cthField.isAccessible = true
            collapsingTextHelper = cthField.get(this)

            val boundsField = collapsingTextHelper!!.javaClass.getDeclaredField("mCollapsedBounds")
            boundsField.isAccessible = true
            bounds = boundsField.get(collapsingTextHelper) as Rect

            recalculateMethod = collapsingTextHelper!!.javaClass.getDeclaredMethod("recalculate")
        } catch (e: NoSuchFieldException) {
            collapsingTextHelper = null
            bounds = null
            recalculateMethod = null
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            collapsingTextHelper = null
            bounds = null
            recalculateMethod = null
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            collapsingTextHelper = null
            bounds = null
            recalculateMethod = null
            e.printStackTrace()
        }

        setHintTextAppearance(R.style.FloatingTextInputLayoutAccent)
        typeface = CustomFont.setFontSemiBold(context.assets)
    }

    private fun adjustBounds() {
        if (collapsingTextHelper == null) {
            return
        }

        try {
            bounds!!.left = editText!!.left + editText!!.paddingLeft
            recalculateMethod!!.invoke(collapsingTextHelper)
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }

    }
}