package com.myd.sccasestudy.di

import com.myd.sccasestudy.core.base.TimerManager
import com.myd.sccasestudy.core.base.baseinterface.ITimerManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {
    @Singleton
    @Provides
    fun provideTimerManager(): ITimerManager = TimerManager()
}