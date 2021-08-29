package com.myd.sccasestudy.home.data.repository

import com.myd.sccasestudy.core.model.Result
import com.myd.sccasestudy.core.source.DataSource
import com.myd.sccasestudy.core.source.FetchCompletionHandler
import com.myd.sccasestudy.core.source.FetchError
import com.myd.sccasestudy.core.source.FetchResponse
import com.myd.sccasestudy.home.data.model.PeopleState
import com.myd.sccasestudy.home.interactor.PeopleRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PeopleRepositoryImp @Inject constructor(
    private val dataSource: DataSource
) : PeopleRepository {
    override suspend fun getPeople(next: String?): Result<PeopleState> {
        return suspendCoroutine { call ->
            dataSource.fetch(next, object : FetchCompletionHandler {
                override fun invoke(fr: FetchResponse?, fe: FetchError?) {
                    fr?.let {
                        call.resume(
                            Result.Success(
                                PeopleState(
                                    fetchResponse = fr
                                )
                            )
                        )
                    }
                    fe?.let {
                        call.resume(
                            Result.Error(
                                fe.errorDescription
                            )
                        )
                    }
                }
            })
        }
    }
}