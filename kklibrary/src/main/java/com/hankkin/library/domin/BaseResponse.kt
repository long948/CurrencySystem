package com.hankkin.library.domin


data class BaseResponse<T>(val errorCode: Int, val errorMsg: String,val data: T)