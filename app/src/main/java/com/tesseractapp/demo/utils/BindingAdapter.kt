package com.tesseractapp.demo.utils

import android.graphics.drawable.Drawable
import android.renderscript.RenderScript
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import com.tesseractapp.demo.R
import com.tesseractapp.demo.customVIew.MyTextInputEditTextRegular
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

import com.bumptech.glide.load.resource.bitmap.CenterCrop

import com.bumptech.glide.request.RequestOptions


object BindingAdapter {

    @JvmStatic
    @androidx.databinding.BindingAdapter("errorText")
    fun setError(editText: TextView, strOrResId: Any?) {
        if (strOrResId is Int) {
            editText.text = editText.context.getString(strOrResId)
        } else {
            editText.text = strOrResId as? String
        }
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("onFocus")
    fun bindFocusChange(
        editText: TextInputEditText,
        onFocusChangeListener: View.OnFocusChangeListener
    ) {
        if (editText.onFocusChangeListener == null) {
            editText.onFocusChangeListener = onFocusChangeListener
        }
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("drawableLeftValidation")
    fun setDrawableLeftByValidation(editText: MyTextInputEditTextRegular, isValid: Boolean) {
        if (editText.text!!.isNotEmpty()) {
            val leftDrawable = if (isValid) {
                ContextCompat.getDrawable(editText.context, R.drawable.ic_check)
            } else {
                ContextCompat.getDrawable(editText.context, R.drawable.ic_warning)
            }
            setIntrinsicBounds(leftDrawable)
            editText.setCompoundDrawables(null, null, leftDrawable, null)
        }
    }

    private fun setIntrinsicBounds(drawable: Drawable?) {
        drawable?.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("loadImg")
    fun setImgSRCString(imageView: ImageView, path: String) {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(20))
        Glide.with(imageView.context)
            .load(path)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .apply(requestOptions).into(imageView)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("loadImage")
    fun setImage(imageView: ImageView, path: String) {

        Glide.with(imageView.context)
            .load(path)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher).into(imageView)
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("loadDrawable")
    fun setDrawable(imageView: ImageView, path: Int) {
        Glide.with(imageView.context)
            .load(path)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(imageView)
    }

}
