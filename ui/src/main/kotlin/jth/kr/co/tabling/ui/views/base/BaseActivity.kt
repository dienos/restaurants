package jth.kr.co.tabling.ui.views.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding?> : AppCompatActivity() {
    @LayoutRes
    abstract fun getLayoutResId(): Int
    abstract fun initializeViewModel()
    abstract fun initializeUiEvent()

    var binding: T? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutResId())
        binding?.lifecycleOwner = this
        initializeViewModel()
        initializeUiEvent()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}