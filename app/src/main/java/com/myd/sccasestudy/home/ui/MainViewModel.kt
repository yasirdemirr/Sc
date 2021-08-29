package com.myd.sccasestudy.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myd.sccasestudy.core.model.Result
import com.myd.sccasestudy.home.interactor.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val peopleUseCase: GetPeopleUseCase
) : ViewModel() {
    fun getPeople() {
        viewModelScope.launch {
            when (val result = peopleUseCase.execute(
                GetPeopleUseCase.Params("yas")
            )) {
                is Result.Success -> {
                    val x = result.payload
                    val y = x
                }
                is Result.Error -> {
                    val x = result.error
                    val y = x
                }
            }
        }
    }
}