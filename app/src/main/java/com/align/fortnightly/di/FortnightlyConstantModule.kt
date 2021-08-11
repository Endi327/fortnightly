package com.align.fortnightly.di

import com.align.domain.ConstantsProvider
import com.align.fortnightly.FortnightlyConstansProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface FortnightlyConstantModule {

    @Binds
    fun bindApsConstantsProvider(binding: FortnightlyConstansProvider): ConstantsProvider
}
