package com.bassem.weathercompose.di

import com.bassem.domain.repo.WeatherRepo
import com.bassem.domain.usecase.FetchWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun provideFetchWeatherUseCase(repo: WeatherRepo) = FetchWeatherUseCase(repo)

}