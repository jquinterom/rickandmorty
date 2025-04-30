package co.mrcomondev.pro.rickandmorty.di

import co.mrcomondev.pro.rickandmorty.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by gesoft
 */
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
  @Singleton
  @Provides
  fun provideRetrofit() = Retrofit
    .Builder()
    .baseUrl(Constants.URL_RICK_AND_MORTY)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
}