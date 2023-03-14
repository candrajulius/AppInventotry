package com.candra.projectinventory.database

import androidx.room.*
import com.candra.projectinventory.data.Barang
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Barang)

    @Delete
    suspend fun deleteProduct(product: Barang)

    @Update
    suspend fun updateProduct(product: Barang)

    @Query("select * from table_barang order by id asc")
    fun showAllProduct(): Flow<List<Barang>>

    @Query("select * from table_barang where status_barang like :statusProduct order by id asc")
    fun searchStatusProduct(statusProduct: String): Flow<List<Barang>>


}