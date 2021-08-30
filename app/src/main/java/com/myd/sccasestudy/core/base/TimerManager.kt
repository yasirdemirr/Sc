package com.myd.sccasestudy.core.base

import android.os.CountDownTimer
import com.myd.sccasestudy.core.base.baseinterface.CountDownTimerListener
import com.myd.sccasestudy.core.base.baseinterface.ITimerManager

class TimerManager : ITimerManager {

    private lateinit var countDownTimer: CountDownTimer

    private var intervalTimer: Long = 0

    companion object {
        private const val TOTAL_TIME = 30000L
    }

    override fun addTimer(listener: CountDownTimerListener) {
        if (intervalTimer == 0L) {
            intervalTimer = TOTAL_TIME
        }
        countDownTimer = object : CountDownTimer(intervalTimer, 1000L) {
            override fun onTick(time: Long) {
                intervalTimer = time
            }

            override fun onFinish() {
                listener.onFinish()
                intervalTimer = 0L
                cancel()
            }

        }
    }

    override fun starTimer() {
        countDownTimer.start()
    }
}