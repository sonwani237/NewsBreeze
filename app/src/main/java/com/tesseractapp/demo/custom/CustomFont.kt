package com.tesseractapp.demo.custom

import android.content.res.AssetManager
import android.graphics.Typeface

object CustomFont{


    fun setFontRegular(assetManager: AssetManager): Typeface {
        return Typeface.createFromAsset(assetManager, "fonts/OpenSans-Regular.ttf")
    }

    fun setFontSemiBold(assetManager: AssetManager): Typeface {
        return Typeface.createFromAsset(assetManager, "fonts/OpenSans-Semibold.ttf")
    }

    fun setFontBold(assetManager: AssetManager): Typeface {
        return Typeface.createFromAsset(assetManager, "fonts/OpenSans-Bold.ttf")
    }
}