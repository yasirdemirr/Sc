package com.myd.sccasestudy.home.data.model

import com.myd.sccasestudy.core.source.FetchError
import com.myd.sccasestudy.core.source.FetchResponse

data class PeopleState(
    val fetchResponse: FetchResponse? = null,
    val fetchError: FetchError? = null
)