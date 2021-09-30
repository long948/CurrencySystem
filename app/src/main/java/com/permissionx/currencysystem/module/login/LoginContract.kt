package com.permissionx.currencysystem.module.login

import com.hankkin.library.mvp.contract.IBaseLoading
import com.hankkin.library.mvp.contract.IPresenterContract
import com.permissionx.currencysystem.bean.LoginBean

class LoginContract {
    interface IView :IBaseLoading{
        fun loginResult(data:LoginBean)
    }
    interface IPresenter : IPresenterContract{
        fun loginPwd(mobile :String,password :String)
    }
}