package co.mrcomondev.pro.rickandmorty.di

import co.mrcomondev.pro.rickandmorty.common.Constants
import co.mrcomondev.pro.rickandmorty.dataaccess.dtos.CharacterDto
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.api.services.ApiService
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.ApiResponseMapper
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.CharacterMapper
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.EpisodeMapper
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.LocationMapper
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.mappers.OriginMapper
import co.mrcomondev.pro.rickandmorty.dataaccess.remote.repository.CharacterRepositoryImpl
import co.mrcomondev.pro.rickandmorty.domain.models.CharacterDomain
import co.mrcomondev.pro.rickandmorty.domain.repository.CharacterRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date
import javax.inject.Singleton

/**
 * Created by gesoft
 */
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

  @Singleton
  @Provides
  fun provideMoshi(): Moshi {
    return Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
      .build()
  }

  @Singleton
  @Provides
  fun provideRetrofit(moshi: Moshi): Retrofit {
    return Retrofit.Builder()
      .baseUrl(Constants.URL_RICK_AND_MORTY)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
  }

  @Provides
  @Singleton
  fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
  }

  @Provides
  @Singleton
  fun provideOriginMapper(): OriginMapper {
    return OriginMapper()
  }

  @Provides
  @Singleton
  fun provideLocationMapper(): LocationMapper {
    return LocationMapper()
  }

  @Provides
  @Singleton
  fun provideCharacterMapper(
    originMapper: OriginMapper,
    locationMapper: LocationMapper
  ): CharacterMapper {
    return CharacterMapper(originMapper, locationMapper)
  }

  @Provides
  @Singleton
  fun provideApiResponseMapper(characterMapper: CharacterMapper): ApiResponseMapper<CharacterDto, CharacterDomain> {
    return ApiResponseMapper(characterMapper)
  }

  @Provides
  @Singleton
  fun provideEpisodeMapper(): EpisodeMapper {
    return EpisodeMapper()
  }

  @Provides
  @Singleton
  fun provideCharacterRepository(
    apiService: ApiService,
    provideCharacterMapper: CharacterMapper,
    provideApiResponseMapper: ApiResponseMapper<CharacterDto, CharacterDomain>,
    provideEpisodeMapper: EpisodeMapper
  ):
      CharacterRepository {
    return CharacterRepositoryImpl(
      apiService,
      provideCharacterMapper,
      provideApiResponseMapper,
      provideEpisodeMapper
    )
  }
}