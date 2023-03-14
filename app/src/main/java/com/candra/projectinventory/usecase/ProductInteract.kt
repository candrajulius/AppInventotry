package com.candra.projectinventory.usecase

import android.content.Context
import com.candra.projectinventory.data.Barang
import com.candra.projectinventory.repository.IRepository
import com.candra.projectinventory.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductInteract @Inject constructor(
    private val repository: IRepository
): ProductUseCase
{
    override fun showAllProduct(): Flow<List<Barang>> =
        repository.showAllProduct()

    override suspend fun insertProduct(product: Barang) =
        repository.insertProduct(product)

    override suspend fun deleteItemProduct(product: Barang) =
        repository.deleteItemProduct(product)

    override suspend fun updateProduct(product: Barang) =
        repository.updateProduct(product)

    override fun showProductBasedStatus(status: String): Flow<List<Barang>>  =
        repository.showAllProductBasedStatus(status)


}