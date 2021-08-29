package com.myd.sccasestudy.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<DB : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {
    private lateinit var binding: DB

    private lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()
        binding = DataBindingUtil.setContentView(this, provideLayoutResId())
        bindViewModel(binding)
    }

    abstract fun provideViewModel(): VM

    abstract fun provideLayoutResId(): Int

    abstract fun bindViewModel(dataBinding: DB)

    fun getViewModel() = viewModel

    fun getBinding() = binding
}