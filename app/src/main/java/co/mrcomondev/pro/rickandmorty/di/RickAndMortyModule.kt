package co.mrcomondev.pro.rickandmorty.di

import co.mrcomondev.pro.rickandmorty.dataaccess.services.ApiService
import co.mrcomondev.pro.rickandmorty.dataaccess.services.ApiServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by gesoft
 */

@InstallIn(SingletonComponent::class)
@Module
abstract class RickAndMortyApiModule {
  @Singleton
  @Binds
  abstract fun bindRickAndMortyBindService(impl: ApiServiceImpl): ApiService
}