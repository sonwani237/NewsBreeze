package com.investorapp.others

import android.app.Activity
import com.google.android.material.textfield.TextInputEditText
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import com.tesseractapp.demo.R

class SetDefaultDrawables {
    companion object {
        fun setDrawableRightValidated(act: Activity, et: AutoCompleteTextView) {
            val drawable = act.resources.getDrawable(R.drawable.ic_field_validated)
            drawable.setBounds(0, 0, 32, 32)
            et.setCompoundDrawables(et.compoundDrawables[0], null, drawable, null)
        }

        fun setDrawableRightBlankValidated(act: Activity, et: TextInputEditText) {
            et.setCompoundDrawables(et.compoundDrawables[0], null, null, null)
        }
        fun setDrawableRightValidatedDropDown(act: Activity, et: TextInputEditText) {
            et.setCompoundDrawables(et.compoundDrawables[0], null, et.compoundDrawables[2], null)
        }
        fun setDrawableRightBlankValidated(act: Activity, et: AutoCompleteTextView) {
            et.setCompoundDrawables(et.compoundDrawables[0], null, null, null)
        }
        fun setDrawable(act: Activity, et: EditText, leftDrawableId: Int) {
            val drawable = act.resources.getDrawable(leftDrawableId)
            drawable.setBounds(0, 0, 32, 32)
            et.setCompoundDrawables(drawable, null, null, null)
        }

        fun setDrawableHeading(act: Activity, et: TextView, leftDrawableId: Int) {
            val drawable = act.resources.getDrawable(leftDrawableId)
            drawable.setBounds(0, 0, 32, 32)
            et.setCompoundDrawables(drawable, null, null, null)
        }
        fun setDrawableRightValidated(act: Activity, et: TextInputEditText) {
            val drawable = act.resources.getDrawable(R.drawable.ic_field_validated)
            drawable.setBounds(0, 0, 32, 32)
            et.setCompoundDrawables(et.compoundDrawables[0], null, drawable, null)
        }
        fun setDrawableRightError(act: Activity, et: TextInputEditText) {
            val drawable = act.resources.getDrawable(R.drawable.ic_field_error)
            drawable.setBounds(0, 0, 32, 32)
            et.setCompoundDrawables(et.compoundDrawables[0], null, drawable, null)
        }
        fun setDrawableRightDropDown(act: Activity, et: TextInputEditText) {
            val drawable = act.resources.getDrawable(R.drawable.ic_drop_down)
            drawable.setBounds(0, 0, 48, 32)
            et.setCompoundDrawables(et.compoundDrawables[0], null, drawable, null)
        }

        fun setDrawableRightUpload(act: Activity, et: TextInputEditText) {
            val drawable = act.resources.getDrawable(R.drawable.ic_document_upload)
            drawable.setBounds(0, 0, 32, 32)
            et.setCompoundDrawables(et.compoundDrawables[0], null, drawable, null)
        }


    }
}