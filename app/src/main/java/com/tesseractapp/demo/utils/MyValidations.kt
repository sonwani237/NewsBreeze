package com.tesseractapp.demo.utils

import android.app.Activity
import com.google.android.material.textfield.TextInputEditText
import android.text.TextUtils
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.TextView
import com.investorapp.others.SetDefaultDrawables
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


object MyValidations {
    private val EMAIL_ADDRESS_PATTERN = Pattern.compile(

        "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@" + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|" + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")

    private val EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private val VALID_IFSC = Pattern.compile("^[A-Za-z]{4}[a-zA-Z0-9]{7}\$")
    val NAME_PATTERN = Pattern.compile("^[a-zA-Z ]{2,50}$")

    private val PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9+_.]*\${4,16}")

    private val MobilePattern = Pattern.compile("^[0-9+]{7,15}$")

    private val PIN_CODE = Pattern.compile("^[1-9][0-9]{5}$")

    //old VALID_PASS
    //Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,14}\$")
    private val VALID_PASS = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[.@#£_&\\-+()/*\"':;!?~`|•√π÷×¶∆€¥\$¢^°=\\]{}\\%©®™✓\\[<>]).{8,14}\$")
    // Minimum eight and maximum 14 characters, at least one uppercase letter, one lowercase letter, one number and one special character:

    private var VALID_PAN = Pattern.compile("[A-Z]{3}[P|C|A|F|H|T]{1}[A-Z]{1}[0-9]{4}[A-Z]{1}")
    private var VALID_VOTER = Pattern.compile("[A-Z]{3}[0-9]{7}")
    private var VALID_VOTER_TWO = Pattern.compile("[A-Z]{2}/[0-9]{2}/[0-9]{3}/[0-9]{6}")
    private var GSTIN = Pattern.compile("/^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}\$/")


    fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun checkPassword(password: String): Boolean {
        return VALID_PASS.matcher(password).matches()
    }

    fun checkMobile(mobile: String): Boolean {
        return MobilePattern.matcher(mobile).matches()
    }

    fun checkName(name: String): Boolean {
        //       return NAME_PATTERN.matcher(name).matches()
        return true
    }

    fun checkEmail(email: String): Boolean {
        return EMAIL_PATTERN.matcher(email).matches()
    }

    fun checkValidIFSC(IFSC: String): Boolean {
        return VALID_IFSC.matcher(IFSC).matches()
    }

    fun checkPinCode(pinCode: String): Boolean {
        return PIN_CODE.matcher(pinCode).matches()
    }

    fun checkPan(pan: String): Boolean {
        return VALID_PAN.matcher(pan).matches()
    }

    fun checkVoter(voter: String): Boolean {
        if(VALID_VOTER.matcher(voter).matches()) {
            return VALID_VOTER.matcher(voter).matches()
        }else if(VALID_VOTER_TWO.matcher(voter).matches()){
            return VALID_VOTER_TWO.matcher(voter).matches()
        }
        return false
    }

    fun checkGstin(gstin: String): Boolean {
        return GSTIN.matcher(gstin).matches()
    }

    fun setValid(act: Activity, txtError: TextView, et: TextInputEditText) {
        txtError.visibility = View.GONE
        txtError.text = ""
        SetDefaultDrawables.setDrawableRightValidated(act, et)
    }

    fun setValid(act: Activity, txtError: TextView, et: AutoCompleteTextView) {
        txtError.visibility = View.GONE
        txtError.text = ""
        SetDefaultDrawables.setDrawableRightValidated(act, et)
    }

    fun setValidBlank(act: Activity, txtError: TextView, et: TextInputEditText) {
        txtError.visibility = View.GONE
        txtError.text = ""
        SetDefaultDrawables.setDrawableRightBlankValidated(act, et)
    }

    fun setValidDropDown(act: Activity, txtError: TextView, et: TextInputEditText) {
        txtError.visibility = View.GONE
        txtError.text = ""
        SetDefaultDrawables.setDrawableRightValidatedDropDown(act, et)
    }

    fun setNotValid(act: Activity, txtError: TextView, msg: String, et: TextInputEditText) {
        et.requestFocus()
        txtError.text = msg
        txtError.visibility = View.VISIBLE
        SetDefaultDrawables.setDrawableRightError(act, et)
    }

    fun setNotValid(act: Activity, txtError: TextView, msg: String, et: AutoCompleteTextView) {
        et.requestFocus()
        txtError.text = msg
        txtError.visibility = View.VISIBLE
        SetDefaultDrawables.setDrawableRightBlankValidated(act, et)
        //SetDefaultDrawables.setDrawableRightError(act,et)
    }

    fun setNotValidNoCrossDropDown(act: Activity, txtError: TextView, msg: String, et: TextInputEditText) {
        et.requestFocus()
        txtError.text = msg
        txtError.visibility = View.VISIBLE
        //SetDefaultDrawables.setDrawableRightError(act,et)
    }

    fun getDateFromDateFormat(dateTime: String): String {
        val inputPattern = "yyyy-MM-dd HH:mm:ss"
        val outputPattern = "dd-MMM-yyyy"
        val inputFormat = SimpleDateFormat(inputPattern, Locale.getDefault())
        val outputFormat = SimpleDateFormat(outputPattern, Locale.getDefault())

        var date: Date? = null
        var str: String? = null

        try {
            date = inputFormat.parse(dateTime)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return str!!
    }

    fun getTimeFromDateFormat(dateTime: String): String {
        val inputPattern = "yyyy-MM-dd HH:mm:ss"
        val outputPattern = "h:mm a"
        val inputFormat = SimpleDateFormat(inputPattern, Locale.getDefault())
        val outputFormat = SimpleDateFormat(outputPattern, Locale.getDefault())

        var date: Date? = null
        var str: String? = null

        try {
            date = inputFormat.parse(dateTime)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return str!!
    }


}