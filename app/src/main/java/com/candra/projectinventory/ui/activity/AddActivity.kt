package com.candra.projectinventory.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.candra.projectinventory.R
import com.candra.projectinventory.data.Barang
import com.candra.projectinventory.databinding.ActivityAddLayoutBinding
import com.candra.projectinventory.helper.Constant
import com.candra.projectinventory.helper.Utils
import com.candra.projectinventory.ui.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddActivity: AppCompatActivity(){
    private val productViewModel by viewModels<ProductViewModel>()
    private lateinit var addBinding: ActivityAddLayoutBinding
    private var selectedValue = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addBinding = ActivityAddLayoutBinding.inflate(layoutInflater)
        setContentView(addBinding.root)
        addBinding.btnAddData.setOnClickListener {
            validateDataFromDatabase()
        }
        Utils.setToolbar(this@AddActivity,getString(R.string.add_product),supportActionBar,5)
        setDefaultCheckedRadioButton()
    }

    private fun setDefaultCheckedRadioButton(){
        addBinding.rbIn.isChecked = true
    }

    private fun validateDataFromDatabase(){
        addBinding.apply {
            val codeProduct = tilInputCodeProduct.editText?.text.toString()
            val nameProduct = tilInputNameProduct.editText?.text.toString()
            val priceProduct = tilInputPriceProduct.editText?.text.toString()
            val stockProduct = tilInputYourStock.editText?.text.toString()

            selectedValue = when(rgPilihanItem.checkedRadioButtonId){
                R.id.rb_in -> Constant.STATUS_IN
                R.id.rb_out -> Constant.STATUS_OUT
                else -> ""
            }
            if (nameProduct.isEmpty() || codeProduct.isEmpty() || priceProduct.isEmpty()
                || stockProduct.isEmpty()){
                Utils.makeToast(getString(R.string.blank_toast_add_product),this@AddActivity)
            }else{
                validateAddData(nameProduct,codeProduct,priceProduct,stockProduct,selectedValue)
            }
        }
    }

    private fun validateAddData(
        nameProduct: String,codeProduct: String,priceProduct: String,
        stockProduct: String,statusProduct: String
    )
    {
        val product = Barang(0,codeProduct,nameProduct,priceProduct,stockProduct,statusProduct)
        productViewModel.insertProduct(product).also { backToActivityMain() }

    }

    private fun backToActivityMain(){
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
    }
}