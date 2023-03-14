package com.candra.projectinventory.helper

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object Constant {

    const val NAMA_BARANG = "nama_barang"
    const val CODE_BARANG =  "kode_barang"
    const val PRICE_BARANG = "harga_barang"
    const val TABLE_BARANG = "table_barang"
    const val NAME_TOOLBAR_LOGIN = "login"
    const val USERNAME_LOGIN = "NovitaMRG"
    const val PASSWORD_LOGIN = "Simaringga14"
    const val EXTRA_PRODUCT = "extra_barang"
    const val SESSION_USER = "session_user"
    const val STOCK_PRODUCT = "stock_product"
    const val STATUS_PRODUCT = "status_barang"
    private const val LOGIN_USER = "boolean"
    private const val USERNAME_USER = "username"
    const val STATUS_IN = "masuk"
    const val STATUS_OUT = "keluar"
    const val POSITION = "position"
    private const val PASSWORD_USER = "password"
    val USERNAME = stringPreferencesKey(USERNAME_USER)
    val PASSWORD = stringPreferencesKey(PASSWORD_USER)
    val IS_LOGIN = booleanPreferencesKey(LOGIN_USER)
}