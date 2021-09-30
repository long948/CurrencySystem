package com.permissionx.currencysystem.http

import com.permissionx.currencysystem.common.Constant
import com.permissionx.currencysystem.http.Api.LotteryEightApi

interface HttpClientUtils {
    object Builder{
        fun getCommonHttp():LotteryEightApi{
        return ApiFactory.instance.create(LotteryEightApi::class.java,Constant.MelonUrl.BASE_URL)
        }
    }
}