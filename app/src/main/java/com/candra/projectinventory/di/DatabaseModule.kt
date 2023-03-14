package com.candra.projectinventory.di

import android.content.Context
import androidx.room.Room
import com.candra.projectinventory.database.DatabaseBarang
import com.candra.projectinventory.database.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context:Context): DatabaseBarang =
        Room.databaseBuilder(
            context,
            DatabaseBarang::class.java,"product_database.db"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

    @Provides
    fun provideProductDao(database: DatabaseBarang): ProductDao = database.productDao()
}