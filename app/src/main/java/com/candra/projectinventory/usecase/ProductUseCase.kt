package com.candra.projectinventory.usecase

import android.content.Context
import com.candra.projectinventory.data.Barang
import kotlinx.coroutines.flow.Flow

interface ProductUseCase {
    fun showAllProduct(): Flow<List<Barang>>
    suspend fun insertProduct(product: Barang)
    suspend fun deleteItemProduct(product: Barang)
    suspend fun updateProduct(product: Barang)
    fun showProductBasedStatus(status: String): Flow<List<Barang>>
}