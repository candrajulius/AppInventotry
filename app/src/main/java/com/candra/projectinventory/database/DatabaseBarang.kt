package com.candra.projectinventory.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.candra.projectinventory.data.Barang

@Database(entities = [Barang::class], version = 2, exportSchema = false)
abstract class DatabaseBarang: RoomDatabase() {
    abstract fun productDao(): ProductDao
}