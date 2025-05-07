package co.mrcomondev.pro.rickandmorty.di

import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.EpisodeMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by gesoft
 */
@InstallIn(SingletonComponent::class)
@Module
object EpisodeModule {

  @Provides
  @Singleton
  fun provideEpisodeMapper(): EpisodeMapper {
    return EpisodeMapper()
  }
}