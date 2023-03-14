package com.candra.projectinventory.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.candra.projectinventory.R
import com.candra.projectinventory.databinding.ActivityMainBinding
import com.candra.projectinventory.helper.Constant
import com.candra.projectinventory.helper.Utils
import com.candra.projectinventory.ui.viewmodel.ProductViewModel
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<ProductViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbarForLogin()
        binding.apply {
            btnLogin.setOnClickListener {
                validateButton()
            }
        }
    }

    private fun setToolbarForLogin(){
        supportActionBar?.apply {
            title = Constant.NAME_TOOLBAR_LOGIN.uppercase()
        }
    }

    private fun validateButton(){
        val lowercaseUsername = Constant.USERNAME_LOGIN.lowercase()
        val lowercasePassword = Constant.PASSWORD_LOGIN.lowercase()
        binding.apply {
            val usernameLogin = textFieldName.editText?.text.toString()
            val passwordLogin = textYourPassword.editText?.text.toString()

            if (usernameLogin.isEmpty() || passwordLogin.isEmpty()){
                Utils.makeToast(getString(R.string.toast_login),this@MainActivity)
            }else if (usernameLogin != lowercaseUsername || passwordLogin != lowercasePassword){
                Utils.makeToast(getString(R.string.password_and_username_wrong),this@MainActivity)
            }else{
                lifecycleScope.launch {
                    mainViewModel.loginAccount(this@MainActivity,
                        lowercaseUsername,lowercasePassword
                    ).also {
                        startActivity(Intent(this@MainActivity,MenuActivity::class.java)).also {
                            finish()
                        }
                    }
                }
            }
        }
    }

}