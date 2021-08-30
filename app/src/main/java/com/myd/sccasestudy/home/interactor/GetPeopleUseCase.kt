package com.myd.sccasestudy.home.interactor

import com.myd.sccasestudy.core.BaseUseCase
import com.myd.sccasestudy.home.data.model.PeopleState
import javax.inject.Inject

class GetPeopleUseCase @Inject constructor(
    private val peopleRepository: PeopleRepository
) :
    BaseUseCase<PeopleState, GetPeopleUseCase.Params>() {


    override suspend fun execute(params: Params) = peopleRepository.getPeople(params.next)

    data class Params(
        val next: String? = null
    )
}