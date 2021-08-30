package com.myd.sccasestudy.core.base.baseinterface

interface ITimerManager {
    fun addTimer(listener: CountDownTimerListener)
    fun starTimer()
}