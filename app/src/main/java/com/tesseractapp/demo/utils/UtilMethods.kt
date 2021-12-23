package com.tesseractapp.demo.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.tesseractapp.demo.R

object UtilMethods {

    fun printLog(tag : String, message : String){
        Log.e(tag, message)
    }

    fun checkInternetAvailability(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networks = cm.allNetworks
        var hasInternet = false
        if (networks.isNotEmpty()) {
            for (network in networks) {
                val nc = cm.getNetworkCapabilities(network)
                if (nc.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) hasInternet = true
            }
        }
        return hasInternet
    }

    fun showSnackBar(context: Context, message: String?, view: View?) {
        val snackBar: Snackbar = Snackbar.make(view!!, message.toString(), Snackbar.LENGTH_LONG)
        val sbView: View = snackBar.view
        val textView = sbView.findViewById<TextView>(R.id.snackbar_text)
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
        textView.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
        snackBar.show()
    }

    fun snackBarLong(context : Context, message: String?, view: View?) : Snackbar {
        val snackBar = Snackbar.make(view!!, message!!, Snackbar.LENGTH_INDEFINITE)
        val sbView = snackBar.view
        val textView = sbView.findViewById<TextView>(R.id.snackbar_text)
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
        textView.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
        snackBar.show()

        return snackBar
    }

    fun save(sharedPreferences: SharedPreferences, key: String?, data: String?) {
        sharedPreferences.edit().putString(key, data).apply()
    }

    fun <T> getPrefData(sharedPreferences: SharedPreferences, key: String?, returnType: Class<T>?): T? {
        val json = sharedPreferences.getString(key, "")
        return Gson().fromJson(json, returnType)
    }

    fun hideKeyboard(context: Context, view: View) {
        val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}