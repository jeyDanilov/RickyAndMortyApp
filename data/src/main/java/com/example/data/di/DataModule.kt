package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.AppDatabase
import com.example.data.local.CharacterDao
import com.example.data.remote.RickAndMortyApi
import com.example.data.repository.CharacterRepositoryImpl
import com.example.domain.repository.CharacterRepositoryDom
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

// Hilt module that provides app-wide dependencies scoped to SingletonComponent@Module.
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    // Binds the implementation of CharacterRepository to its domain interface.
    @Binds
    @Singleton
    abstract fun bindCharacterRepository(impl: CharacterRepositoryImpl): CharacterRepositoryDom

    companion object {

        // Provides a Moshi instance with Kotlin support for JSON serialization.
        @Provides
        @Singleton
        fun provideMoshi(): Moshi =
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        // Provides an OkHttpClient with logging enabled for debugging network requests.
        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
                .build()

        // Provides a Retrofit instance configured with Moshi and OkHttp for API communication.
        @Provides
        @Singleton
        fun provideRickAndMortyApi(
            moshi: Moshi,
            okHttpClient: OkHttpClient
        ): RickAndMortyApi {
            return Retrofit.Builder()
                .baseUrl(RickAndMortyApi.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
                .create(RickAndMortyApi::class.java)
        }

        // Provides a Room database instance for local data storage.
        @Provides
        @Singleton
        fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "app_database"
            ).build()

        // Provides the DAO for accessing character data from the database.
        @Provides
        fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao =
            appDatabase.characterDao()
    }
}
