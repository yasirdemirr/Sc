package com.myd.sccasestudy.home.di

import com.myd.sccasestudy.home.data.repository.PeopleRepositoryImp
import com.myd.sccasestudy.home.interactor.PeopleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HomePageModule {
    @Binds
    abstract fun getPeople(peopleRepositoryImp: PeopleRepositoryImp): PeopleRepository
}