package jth.kr.co.tabling.ui.views.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import jth.kr.co.tabling.ui.utils.NetworkUtil
import jth.kr.co.tabling.ui.views.dialog.ProgressDialog
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding?> : AppCompatActivity() {
    @LayoutRes
    abstract fun getLayoutResId(): Int
    abstract fun initializeViewModel()
    abstract fun initializeUiEvent()

    @Inject
    lateinit var networkUtil: NetworkUtil

    var binding: T? = null
        private set

    protected val progress = ProgressDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutResId())
        binding?.lifecycleOwner = this
        initializeViewModel()
        initializeUiEvent()
        networkUtil.currentContext = this
        networkUtil.registerNetworkCallback()
    }

    override fun onDestroy() {
        super.onDestroy()
        networkUtil.terminateNetworkCallback(this)
        binding = null
    }
}