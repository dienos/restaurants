package jth.kr.co.tabling.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel : ViewModel() {
    enum class UiEvent(val ui : String) {
        START_DETAIL("start_detail"),
        SET_FAVORITE("set_favorite")
    }

    private val _progress = MutableStateFlow(false)
    val progressFlow = _progress.asStateFlow()

    private val _toast = MutableStateFlow("")
    val toastFlow = _toast.asStateFlow()

    private val _uiEvent = MutableStateFlow("")
    val uiEvent = _uiEvent.asStateFlow()

    fun updateUi(ui: String) {
        _uiEvent.value = ui
    }

    fun setDefaultUi() {
        _uiEvent.value = ""
    }

    fun updateProgress(show: Boolean) {
        _progress.value = show
    }

    fun updateToast(text: String) {
        _toast.value = text
    }
}