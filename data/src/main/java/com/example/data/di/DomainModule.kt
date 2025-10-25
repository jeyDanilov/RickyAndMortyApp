package com.example.data.di


import com.example.domain.repository.CharacterRepositoryDom
import com.example.domain.usecase.FilterCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideFilterCharactersUseCase(
        repository: CharacterRepositoryDom
    ): FilterCharactersUseCase {
        return FilterCharactersUseCase(repository)
    }
}