package com.candra.projectinventory.repository

import android.content.Context
import com.candra.projectinventory.data.Barang
import com.candra.projectinventory.data.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource
): IRepository
{
    override fun showAllProduct(): Flow<List<Barang>> = localDataSource.showAllProduct()

    override suspend fun insertProduct(product: Barang) = localDataSource.insertProduct(product)

    override suspend fun deleteItemProduct(product: Barang) = localDataSource.deleteItemProduct(product)

    override suspend fun updateProduct(product: Barang) = localDataSource.updateProduct(product)

    override fun showAllProductBasedStatus(status: String): Flow<List<Barang>> =
        localDataSource.showAllProductBasedStatusProduct(statusProduct = status)


}