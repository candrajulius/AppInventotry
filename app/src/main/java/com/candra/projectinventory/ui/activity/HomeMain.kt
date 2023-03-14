package com.candra.projectinventory.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.candra.projectinventory.R
import com.candra.projectinventory.data.Barang
import com.candra.projectinventory.databinding.HomeMainBinding
import com.candra.projectinventory.databinding.ProfileLayoutBinding
import com.candra.projectinventory.helper.Constant
import com.candra.projectinventory.helper.Utils
import com.candra.projectinventory.ui.adapter.ProductAdapter
import com.candra.projectinventory.ui.viewmodel.ProductViewModel
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeMain : AppCompatActivity()
{
    private lateinit var homeMainBinding: HomeMainBinding
    private val adapterMain by lazy { ProductAdapter(this@HomeMain,::onClick,::onDelete) }
    private val homeMainViewModel by viewModels<ProductViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMainBinding = HomeMainBinding.inflate(layoutInflater)
        setContentView(homeMainBinding.root)
        setAdapter()
        homeMainBinding.addDataTool.setOnClickListener {
            actionAdd()
        }

        setReceivedDataFromMenu()

    }

    private fun setReceivedDataFromMenu(){
        when(intent.getIntExtra(Constant.POSITION,0)){
            1 -> {
                Utils.setToolbar(this@HomeMain,getString(R.string.home_product),supportActionBar,1)
                homeMainViewModel.showAllProduct.observe(this,this::showAllProduct)
            }
            2 -> {
                Utils.setToolbar(this@HomeMain,getString(R.string.home_product),supportActionBar,2)
                showAllProductBasedStatus("%${Constant.STATUS_IN}%")
            }
            3 -> {
                Utils.setToolbar(this@HomeMain,getString(R.string.home_product),supportActionBar,3)
                showAllProductBasedStatus("%${Constant.STATUS_OUT}%")
            }
        }
    }

    private fun showAllProduct(it:List<Barang>){
        adapterMain.submitListData(it)
    }

    private fun showAllProductBasedStatus(status: String){
        homeMainViewModel.showAllProductBasedStatus(status).observe(this){
            adapterMain.submitListData(it)
        }
    }

    private fun actionAdd(){
        startActivity(Intent(this@HomeMain,AddActivity::class.java))
        finish()
    }

    private fun onClick(data: Barang){
        Intent(this@HomeMain,DetailActivity::class.java).apply {
            putExtra(Constant.EXTRA_PRODUCT,data)
        }.also { startActivity(it) }
    }

    private fun onDelete(data: Barang){
        val builder = AlertDialog.Builder(this@HomeMain)
        builder.apply {
            setPositiveButton(getString(R.string.yes_delete_data)){_,_ ->
                homeMainViewModel.deleteItemProduct(data)
                adapterMain.notifyItemRemoved(data.id)
                Utils.makeToast(getString(R.string.success_delete_data),this@HomeMain)
            }
            setNegativeButton(getString(R.string.no_delete_data)){_,_ ->
                Utils.makeToast(getString(R.string.not_yet_delete_data),this@HomeMain)
            }
            setTitle(getString(R.string.title_delete_data,data.name_barang))
            setMessage(getString(R.string.message_delete_data,data.name_barang))
            create().show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setAdapter(){
        with(homeMainBinding){
            recyclerviewData.apply {
                layoutManager = LinearLayoutManager(this@HomeMain)
                adapter = adapterMain
            }
        }
    }





    override fun onBackPressed() {
        finish()
    }
}