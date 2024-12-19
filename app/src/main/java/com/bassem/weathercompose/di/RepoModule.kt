package com.bassem.weathercompose.di

import com.bassem.data.repo.WeatherRepoImp
import com.bassem.domain.repo.WeatherRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepoModule {

    @Binds
    abstract fun provideRepo(repoImp: WeatherRepoImp): WeatherRepo
}