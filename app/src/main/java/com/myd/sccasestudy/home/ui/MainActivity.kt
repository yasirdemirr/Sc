package com.myd.sccasestudy.home.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.myd.sccasestudy.R
import com.myd.sccasestudy.core.base.BaseActivity
import com.myd.sccasestudy.core.base.PaginationScrollListener
import com.myd.sccasestudy.core.getOnlyUniquePerson
import com.myd.sccasestudy.core.source.FetchResponse
import com.myd.sccasestudy.databinding.ActivityMainBinding
import com.myd.sccasestudy.home.ui.adapter.PeopleAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    SwipeRefreshLayout.OnRefreshListener {

    private val mainViewModel: MainViewModel by viewModels()

    override fun provideLayoutResId() = R.layout.activity_main

    override fun provideViewModel() = mainViewModel


    @Inject
    internal lateinit var peopleAdapter: PeopleAdapter

    override fun bindViewModel(dataBinding: ActivityMainBinding) {
        dataBinding.viewModel = getViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingViewModel()
        bindingUiElements()
        mainViewModel.getPeople(null)
    }

    private fun bindingUiElements() {
        with(getBinding()) {
            swipeRefresh.apply {
                setOnRefreshListener(this@MainActivity)
            }
            retryBtn.setOnClickListener {
                mainViewModel.getPeople(null)
            }
        }
    }

    private fun bindingViewModel() {
        with(getViewModel()) {
            peopleLiveData.observe(
                this@MainActivity, {
                    it.fetchError?.let { errorText ->
                        with(getBinding()) {
                            errorTv.visibility = View.VISIBLE
                            errorTv.text = errorText
                            peopleRv.visibility = View.GONE
                            retryBtn.visibility = View.VISIBLE
                        }
                    }
                    it.fetchResponse?.let { fetchRes ->
                        initPeopleRecycler(fetchRes)
                        getBinding().errorTv.visibility = View.GONE
                        getBinding().peopleRv.visibility = View.VISIBLE
                        getBinding().retryBtn.visibility = View.GONE


                    }
                }
            )
        }
    }

    private fun initPeopleRecycler(fetchRes: FetchResponse) {
        getBinding().peopleRv.apply {
            adapter = peopleAdapter
            peopleAdapter.setPeople(fetchRes.people.getOnlyUniquePerson())
            addOnScrollListener(object :
                PaginationScrollListener(layoutManager as LinearLayoutManager) {
                override fun isLoading() = mainViewModel.isLoading

                override fun onLoadMore() {
                    mainViewModel.getPeople(mainViewModel.paginationValue)
                }
            })
        }

    }

    override fun onRefresh() {
        getBinding().swipeRefresh.isRefreshing = false
        if (!mainViewModel.requestAvailable) {
            return
        }
        peopleAdapter.clearPeople()
        mainViewModel.getPeople(null)
        mainViewModel.initTimer()
    }
}