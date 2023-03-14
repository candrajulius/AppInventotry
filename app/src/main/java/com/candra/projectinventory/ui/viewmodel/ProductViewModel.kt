package com.candra.projectinventory.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.candra.projectinventory.data.Barang
import com.candra.projectinventory.data.LoginUser
import com.candra.projectinventory.helper.Utils
import com.candra.projectinventory.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productCase: ProductUseCase
): ViewModel()
{
    val showAllProduct = productCase.showAllProduct().asLiveData()

    fun insertProduct(product: Barang) = viewModelScope.launch {
        productCase.insertProduct(product)
    }

    fun deleteItemProduct(product: Barang) = viewModelScope.launch {
        productCase.deleteItemProduct(product)
    }

    fun updateProduct(product: Barang) = viewModelScope.launch {
        productCase.updateProduct(product)
    }

    suspend fun loginAccount(context: Context,name: String, password: String) = viewModelScope.launch {
        val loginUser = LoginUser(username = name, password = password, isLogin = true)
        Utils.updateDataUser(context,loginUser)
    }

    suspend fun logoutUser(context: Context) = viewModelScope.launch {
        Utils.updateDataUser(context,LoginUser("","",false))
    }

    fun getUserLogin(context: Context) = Utils.getUserLogin(context).asLiveData()

    fun showAllProductBasedStatus(status: String) = productCase.showProductBasedStatus(status).asLiveData()
}