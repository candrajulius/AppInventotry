package com.candra.projectinventory.di

import com.candra.projectinventory.usecase.ProductInteract
import com.candra.projectinventory.usecase.ProductUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideProductUseCase(productInteract: ProductInteract): ProductUseCase
}