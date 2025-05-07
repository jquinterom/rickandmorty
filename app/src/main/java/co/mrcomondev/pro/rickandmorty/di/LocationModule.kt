package co.mrcomondev.pro.rickandmorty.di

import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.LocationDto
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.api.services.LocationApiService
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.ApiResponseMapper
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.LocationMapper
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.repository.LocationRepositoryImpl
import co.mrcomondev.pro.rickandmorty.domain.models.LocationDomain
import co.mrcomondev.pro.rickandmorty.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by gesoft
 */
@InstallIn(SingletonComponent::class)
@Module
object LocationModule {

  @Provides
  @Singleton
  fun provideLocationApiService(retrofit: Retrofit): LocationApiService {
    return retrofit.create(LocationApiService::class.java)
  }

  @Provides
  @Singleton
  fun provideLocationMapper(): LocationMapper {
    return LocationMapper()
  }

  @Provides
  @Singleton
  fun provideLocationApiResponseMapper(locationMapper: LocationMapper): ApiResponseMapper<LocationDto, LocationDomain> {
    return ApiResponseMapper(locationMapper)
  }

  @Provides
  @Singleton
  fun provideLocationRepository(
    locationApiService: LocationApiService,
    provideApiResponseMapper: ApiResponseMapper<LocationDto, LocationDomain>,
  ): LocationRepository {
    return LocationRepositoryImpl(locationApiService, provideApiResponseMapper)
  }
}