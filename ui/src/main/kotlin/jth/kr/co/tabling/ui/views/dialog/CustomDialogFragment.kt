package jth.kr.co.tabling.ui.views.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import jth.kr.co.tabling.ui.R

class CustomDialogFragment(
    private val message: String = "",
    private val onPositiveButtonClick: () -> Unit = {}
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(message)
                .setPositiveButton(
                    R.string.confirm
                ) { _, _ ->
                    dismiss()
                    onPositiveButtonClick()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}