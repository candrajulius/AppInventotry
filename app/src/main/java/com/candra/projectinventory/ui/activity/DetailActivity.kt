
package com.candra.projectinventory.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.candra.projectinventory.R
import com.candra.projectinventory.data.Barang
import com.candra.projectinventory.databinding.ActivityDetailMainBinding
import com.candra.projectinventory.helper.Constant
import com.candra.projectinventory.helper.Utils
import com.candra.projectinventory.ui.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class DetailActivity : AppCompatActivity(){

    private lateinit var detailBinding: ActivityDetailMainBinding
    private val detailViewModel by viewModels<ProductViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailMainBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
        Utils.setToolbar(
            this@DetailActivity,
            getString(R.string.detail_product),
            supportActionBar,
            4
        )
        receivedData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun receivedData(){
        intent.extras?.getParcelable<Barang>(Constant.EXTRA_PRODUCT)?.let { product ->
            with(detailBinding){
                edtTextYourCodeProduct.setText(product.code_barang)
                edtTextYourProduct.setText(product.name_barang)
                edtTextPriceProduct.setText(product.price_barang)
                edtTextStockProduct.setText(product.stock_product)
                edtTextStatusProduct.setText(product.status_product)
                btnEditData.setOnClickListener {
                    changeData(product.id,product.code_barang,product.status_product)
                }
            }
        }
    }

    private fun changeData(idProduct: Int,codeProduct: String,statusProduct: String){
        detailBinding.apply {
            val nameProduct = tilInputNameProduct.editText?.text.toString()
            val priceProduct = tilInputPriceProduct.editText?.text.toString()
            val stockProduct = tilInputStockProduct.editText?.text.toString()

            if (nameProduct.isEmpty() || priceProduct.isEmpty() || stockProduct.isEmpty()){
                Utils.makeToast(getString(R.string.blank_toast_add_product),this@DetailActivity)
            }else{
                Barang(idProduct,codeProduct,nameProduct,priceProduct,stockProduct,statusProduct).let {
                    detailViewModel.updateProduct(it).also { finish() }
                }
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }
}