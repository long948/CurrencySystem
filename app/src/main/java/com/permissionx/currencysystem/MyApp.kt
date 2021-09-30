package com.permissionx.currencysystem

import android.app.Application
import com.hankkin.library.http.HttpConfig
import com.hankkin.library.http.persistentcookiejar.PersistentCookieJar
import com.hankkin.library.http.persistentcookiejar.cache.SetCookieCache
import com.hankkin.library.http.persistentcookiejar.persistence.SharedPrefsCookiePersistor

import com.hankkin.library.utils.SPUtils
import com.permissionx.currencysystem.common.Constant

class MyApp : Application() {
    companion object{
        private var instance:MyApp?=null
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
        SPUtils.init(this, Constant.COMMON.SP_NAME)
        initHttp()
    }

    private fun initHttp() {
        HttpConfig.setCookie(PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(this)
        ))
        HttpConfig.setParams(mapOf("padAppVer" to BuildConfig.VERSION_CODE))
        HttpConfig.setHeaders(
            mapOf("Authorization" to "Bearer "+SPUtils.getString(Constant.SP_KEY.USER_TOKEN))
        )
    }
}