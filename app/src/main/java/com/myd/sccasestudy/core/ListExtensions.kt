package com.myd.sccasestudy.core

import com.myd.sccasestudy.core.source.Person

fun List<Person>.getOnlyUniquePerson(): List<Person> = this.distinctBy { it.id }
