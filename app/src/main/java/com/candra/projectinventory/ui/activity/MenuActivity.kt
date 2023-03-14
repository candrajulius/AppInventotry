package com.candra.projectinventory.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.candra.projectinventory.R
import com.candra.projectinventory.databinding.ActivityMenuBinding
import com.candra.projectinventory.databinding.ProfileLayoutBinding
import com.candra.projectinventory.helper.Constant
import com.candra.projectinventory.helper.Utils
import com.candra.projectinventory.ui.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MenuActivity: AppCompatActivity(){
    private lateinit var menuBinding: ActivityMenuBinding
    private val menuViewModel by viewModels<ProductViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        menuBinding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(menuBinding.root)
        setToolbar()
        setClickMenuCard()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setToolbar(){
        supportActionBar?.apply {
            title = getString(R.string.menu_home)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.profile){
            showDialogProfile()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDialogProfile(){
        menuViewModel.getUserLogin(this@MenuActivity).observe(this){user ->
            val builder = AlertDialog.Builder(this@MenuActivity).create()
            val view2 = ProfileLayoutBinding.inflate(LayoutInflater.from(this@MenuActivity))
            builder.setView(view2.root)
            view2.edtUsername.setText(user.username)
            view2.edtPassword.setText(user.password)
            view2.btnClose.setOnClickListener {
                builder.dismiss()
            }
            view2.btnLogout.setOnClickListener {
                logoutUser()
            }
            builder.show()
        }

    }

    private fun logoutUser(){
        lifecycleScope.launch{
            menuViewModel.logoutUser(this@MenuActivity).also { startActivity(
                Intent(this@MenuActivity,MainActivity::class.java)
            )
                finish()
            }
        }
    }

    private fun setClickMenuCard(){
        menuBinding.allProduct.setOnClickListener {
           setSendDataForToolbar(1)
        }
        menuBinding.comingProduct.setOnClickListener {
           setSendDataForToolbar(2)
        }
        menuBinding.outProduct.setOnClickListener {
            setSendDataForToolbar(3)
        }
    }

    private fun setSendDataForToolbar(position: Int){
        Intent(this@MenuActivity,HomeMain::class.java).apply {
            putExtra(Constant.POSITION,position)
        }.also { startActivity(it) }
    }

}