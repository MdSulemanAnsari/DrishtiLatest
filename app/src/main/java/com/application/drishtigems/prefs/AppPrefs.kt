package com.application.drishtigems.prefs

import android.content.Context
import android.content.SharedPreferences
import com.application.drishtigems.Network.StaffNetwork.ApiModel.LoginUserModel.UserModel
import com.application.drishtigems.Network.StaffNetwork.ApiModel.MyProfileModel.MyProfileModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AppPrefs(val ctx: Context) {

    private fun getPrefs(): SharedPreferences {
        return ctx.getSharedPreferences("model", Context.MODE_PRIVATE)
    }
/*    private fun updatePre(): SharedPreferences {
        return ctx.getSharedPreferences("update", Context.MODE_PRIVATE)
    }*/

    fun setString(key: String,value:String) {
        val edit = getPrefs().edit()
        edit.putString(key, value)
        edit.apply()
    }
    fun getStringKey(key: String) :String?{
        return getPrefs().getString(key,"")
    }

    fun setModelKey(key: String, value: UserModel?) {
        val gson = Gson()
        val edit = getPrefs().edit()
        edit.putString(key, gson.toJson(value))
        edit.apply()
    }

    fun getModelKey(key: String) :UserModel?{
        val gson = Gson()
        val type = object  : TypeToken<UserModel>(){}.type
        return gson.fromJson(getPrefs().getString(key,""),type)
    }
    
    fun setUpdate(key: String, value: MyProfileModel?) {
        val gson = Gson()
        val edit = getPrefs().edit()
        edit.putString(key, gson.toJson(value))
        edit.apply()
    }

    fun getUpdate(key: String) :MyProfileModel?{
        val gson = Gson()
        val type = object  : TypeToken<MyProfileModel>(){}.type
        return gson.fromJson(getPrefs().getString(key,""),type)
    }

    fun setToken(key : String,value: String){
        val edit = getPrefs().edit()
        edit.putString(key, value)
        edit.apply()
    }

    fun getToken(key: String) :String?{
        return getPrefs().getString(key,"")
    }
 /*   fun deleteData(){
        setString(EMAIL_ADDRESS,"")
    }
*/
    fun  delete(): Boolean {
        return getPrefs().edit().clear().commit()
    }
}