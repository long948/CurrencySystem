package com.permissionx.currencysystem.module.mine

import com.hankkin.library.mvp.contract.IBaseLoading
import com.hankkin.library.mvp.contract.IPresenterContract

interface UserInfoContract {
    interface IView :IBaseLoading{
         fun updateProfileResult()
         fun uploadImageResult(data: String)
    }
    interface IPresenter:IPresenterContract{
        fun updateProfile(name: String, avatar: String)

        fun uploadImage(path: String)
    }


}