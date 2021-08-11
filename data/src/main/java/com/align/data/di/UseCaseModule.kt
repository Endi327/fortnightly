package com.align.data.di

import com.align.data.usecase.DefaultGetArticlesUseCase
import com.align.domain.usecases.GetArticlesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    internal abstract fun bindGetArticlesUseCase(
        impl: DefaultGetArticlesUseCase
    ): GetArticlesUseCase
}