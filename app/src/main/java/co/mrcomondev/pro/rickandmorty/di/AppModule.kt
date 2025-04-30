package co.mrcomondev.pro.rickandmorty.di

import co.mrcomondev.pro.rickandmorty.common.Constants
import co.mrcomondev.pro.rickandmorty.dataaccess.api.services.ApiService
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
}