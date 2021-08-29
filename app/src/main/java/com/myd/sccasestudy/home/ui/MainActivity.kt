package com.myd.sccasestudy.home.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.myd.sccasestudy.R
import com.myd.sccasestudy.core.base.BaseActivity
import com.myd.sccasestudy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun provideLayoutResId() = R.layout.activity_main

    override fun provideViewModel() = mainViewModel

    override fun bindViewModel(dataBinding: ActivityMainBinding) {
        dataBinding.viewModel = getViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().getPeople()
    }
}