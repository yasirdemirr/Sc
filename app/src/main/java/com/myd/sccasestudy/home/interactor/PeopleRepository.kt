package com.myd.sccasestudy.home.interactor

import com.myd.sccasestudy.core.model.Result
import com.myd.sccasestudy.home.data.model.PeopleState

interface PeopleRepository {
    suspend fun getPeople(next: String?): Result<PeopleState>
}