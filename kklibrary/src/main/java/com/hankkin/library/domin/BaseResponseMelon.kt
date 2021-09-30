package com.hankkin.library.domin

data class  BaseResponseMelon<T>(val msg: String, val code: Int, val data: T)
