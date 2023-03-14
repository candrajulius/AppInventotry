package com.candra.projectinventory.helper

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.candra.projectinventory.R
import com.candra.projectinventory.data.LoginUser
import com.candra.projectinventory.helper.Constant.IS_LOGIN
import com.candra.projectinventory.helper.Constant.PASSWORD
import com.candra.projectinventory.helper.Constant.USERNAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object Utils {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constant.SESSION_USER)

    fun getUserLogin(context: Context): Flow<LoginUser>{
        return context.dataStore.data.map { prefrences ->
            LoginUser(
                username = prefrences[USERNAME],
                password = prefrences[PASSWORD],
                isLogin = prefrences[IS_LOGIN]
            )
        }
    }

    suspend fun updateDataUser(context: Context,user: LoginUser) = context.dataStore.edit { prefrences ->
        user.username?.let { prefrences[USERNAME] = it}
        user.password?.let { prefrences[PASSWORD] = it }
        user.isLogin?.let { prefrences[IS_LOGIN] = it }
    }



    fun setToolbar(context: Context,nameToolbar: String, actionBar: ActionBar?,position: Int){
        when (position) {
            1 -> { setActionBar(nameToolbar,context.getString(R.string.all_product),actionBar) }
            2 -> { setActionBar(nameToolbar,context.getString(R.string.product_in),actionBar) }
            3 -> {
                setActionBar(nameToolbar,context.getString(R.string.product_out),actionBar)
            }
            else -> {
                actionBar?.apply {
                    title = nameToolbar
                    setDisplayHomeAsUpEnabled(true)
                }
            }
        }
    }

    private fun setActionBar(titleToolbar: String, subtitleToolbar: String, actionBar: ActionBar?){
        actionBar?.apply {
            title = titleToolbar
            subtitle = subtitleToolbar
            setDisplayHomeAsUpEnabled(true)
        }
    }

    fun makeToast(message: String, context: Context){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}