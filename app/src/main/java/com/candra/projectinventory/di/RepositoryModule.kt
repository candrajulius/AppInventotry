package com.candra.projectinventory.di

import com.candra.projectinventory.repository.IRepository
import com.candra.projectinventory.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Binds
    abstract fun provideRepository(repository: Repository):IRepository
}