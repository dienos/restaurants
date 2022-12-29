package jth.kr.co.tabling.ui.extensions

import androidx.fragment.app.FragmentManager
import jth.kr.co.tabling.ui.views.base.ProgressDialog

fun ProgressDialog.show(manager : FragmentManager) {
    show(manager, "progress")
}

fun ProgressDialog.close() {
    dismiss()
}