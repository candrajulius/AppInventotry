package com.candra.projectinventory.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.candra.projectinventory.helper.Constant.CODE_BARANG
import com.candra.projectinventory.helper.Constant.NAMA_BARANG
import com.candra.projectinventory.helper.Constant.PRICE_BARANG
import com.candra.projectinventory.helper.Constant.STATUS_PRODUCT
import com.candra.projectinventory.helper.Constant.STOCK_PRODUCT
import com.candra.projectinventory.helper.Constant.TABLE_BARANG
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_BARANG)
data class Barang(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = CODE_BARANG)
    val code_barang: String,

    @ColumnInfo(name = NAMA_BARANG)
    val name_barang: String,

    @ColumnInfo(name = PRICE_BARANG)
    val price_barang: String,

    @ColumnInfo(name = STOCK_PRODUCT)
    val stock_product: String,

    @ColumnInfo(name = STATUS_PRODUCT)
    val status_product: String
): Parcelable