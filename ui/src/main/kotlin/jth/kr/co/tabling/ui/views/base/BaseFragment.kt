package jth.kr.co.tabling.ui.views.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    @LayoutRes
    abstract fun getLayoutResId(): Int
    abstract fun initializeViewModel()
    abstract fun initializeUi()

    var binding: T? = null
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.lifecycleOwner = this@BaseFragment
        initializeViewModel()
        initializeUi()
        super.onViewCreated(view, savedInstanceState)
    }
}