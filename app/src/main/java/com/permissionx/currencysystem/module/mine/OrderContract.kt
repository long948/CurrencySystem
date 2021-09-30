package com.permissionx.currencysystem.module.mine

import com.hankkin.library.mvp.contract.IBaseLoading
import com.hankkin.library.mvp.contract.IPresenterContract
import com.permissionx.currencysystem.bean.OrderEntity

interface OrderContract {
    interface IView : IBaseLoading{
        fun getDataResult(data:List<OrderEntity>)
    }

    interface IPresenter :IPresenterContract{
        fun getData(page:String,status:String)
    }

}