package com.permissionx.currencysystem.fragment

import com.hankkin.library.mvp.contract.IBaseLoading
import com.hankkin.library.mvp.contract.IPresenterContract
import com.permissionx.currencysystem.bean.UserBean

interface MineContract {
    interface IView:IBaseLoading{
      fun  getUserDataResult(data :UserBean?)
    }
    interface IPresenter :IPresenterContract{
    fun  getUserData()
    }

}