package com.candra.projectinventory.repository

import android.content.Context
import com.candra.projectinventory.data.Barang
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun showAllProduct(): Flow<List<Barang>>
    suspend fun insertProduct(product: Barang)
    suspend fun deleteItemProduct(product: Barang)
    suspend fun updateProduct(product: Barang)
    fun showAllProductBasedStatus(status: String): Flow<List<Barang>>
}