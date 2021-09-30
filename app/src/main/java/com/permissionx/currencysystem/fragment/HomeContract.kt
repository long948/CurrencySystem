package com.permissionx.currencysystem.fragment

import com.hankkin.library.mvp.contract.IBaseLoading
import com.hankkin.library.mvp.contract.IPresenterContract
import com.permissionx.currencysystem.bean.InvitationList

interface HomeContract {
    interface IView : IBaseLoading{
      fun invitationListResult(data:List<InvitationList>)
    }
    interface IPresenter : IPresenterContract{
      fun invitationList()
    }
}
