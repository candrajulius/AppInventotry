package com.candra.projectinventory.data

import android.content.Context
import com.candra.projectinventory.database.ProductDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val productDao: ProductDao
){
    fun showAllProduct(): Flow<List<Barang>> = productDao.showAllProduct()
    suspend fun insertProduct(product: Barang) = productDao.insertProduct(product)
    suspend fun updateProduct(product: Barang) = productDao.updateProduct(product)
    suspend fun deleteItemProduct(product: Barang) = productDao.deleteProduct(product)
    fun showAllProductBasedStatusProduct(statusProduct: String): Flow<List<Barang>> =
        productDao.searchStatusProduct(statusProduct)
}