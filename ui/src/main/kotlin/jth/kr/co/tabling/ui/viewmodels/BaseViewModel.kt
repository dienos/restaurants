package jth.kr.co.tabling.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel : ViewModel() {
    enum class UiEvent(val ui: String) {
        START_DETAIL("start_detail"),
        SET_FAVORITE("set_favorite")
    }

    private val progress = MutableStateFlow(false)
    val progressFlow
        get() = progress.asStateFlow()

    private val toast = MutableStateFlow("")
    val toastFlow
        get() = toast.asStateFlow()

    private val uiEvent = MutableStateFlow("")
    val uiEventFlow
        get() = uiEvent.asStateFlow()

    fun updateUi(ui: String) {
        uiEvent.value = ui
    }

    fun setDefaultUi() {
        uiEvent.value = ""
    }

    fun updateProgress(show: Boolean) {
        progress.value = show
    }

    fun updateToast(text: String) {
        toast.value = text
    }
}