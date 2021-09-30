package com.permissionx.currencysystem.http

import com.hankkin.library.http.HttpClient

class ApiFactory {
    companion object{
        var instance :ApiFactory
        init {
            instance= ApiFactory()
        }
    }
    private var commonApi:Any?=null

    fun <T> create(clazz: Class<T>,type:String):T{
        if (commonApi==null){
            synchronized(ApiFactory::class.java){
                commonApi=HttpClient.getBuilder(type).build().create(clazz)
            }
        }
        return commonApi as T
    }
}