package com.myd.sccasestudy.home.ui

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myd.sccasestudy.core.base.baseinterface.CountDownTimerListener
import com.myd.sccasestudy.core.base.baseinterface.ITimerManager
import com.myd.sccasestudy.core.model.Result
import com.myd.sccasestudy.home.data.model.PeopleState
import com.myd.sccasestudy.home.interactor.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val peopleUseCase: GetPeopleUseCase,
    private val timerManager: ITimerManager
) : ViewModel(), CountDownTimerListener {

    var paginationValue: String? = null
    var isLoading = true
    var isLoadingObservable = ObservableInt(View.GONE)

    var requestAvailable = true

    private val peopleMutableLiveData = MutableLiveData<PeopleState>()
    val peopleLiveData: LiveData<PeopleState> = peopleMutableLiveData


    fun getPeople(nextValue: String?) {
        isLoadingObservable.set(View.VISIBLE)
        viewModelScope.launch {
            when (val result = peopleUseCase.execute(
                GetPeopleUseCase.Params(nextValue)
            )) {
                is Result.Success -> {
                    if (result.payload?.fetchResponse?.people.isNullOrEmpty()) {
                        peopleMutableLiveData.value = PeopleState(
                            fetchError = "No One Is Here"
                        )
                        isLoadingObservable.set(View.GONE)
                        return@launch
                    }
                    paginationValue = result.payload?.fetchResponse?.next
                    peopleMutableLiveData.value = PeopleState(
                        fetchResponse = result.payload?.fetchResponse
                    )
                    isLoadingObservable.set(View.GONE)
                }
                is Result.Error -> {
                    peopleMutableLiveData.value = PeopleState(
                        fetchError = result.error
                    )
                    isLoadingObservable.set(View.GONE)
                }

            }
        }
    }

    fun initTimer() {
        timerManager.addTimer(this)
        timerManager.starTimer()
        requestAvailable = false
    }

    override fun onFinish() {
        requestAvailable = true
    }
}