package com.permissionx.currencysystem.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.hankkin.library.mvp.view.MvpFragment
import com.hankkin.library.utils.LogUtils
import com.hankkin.library.utils.SPUtils
import com.permissionx.currencysystem.R
import com.permissionx.currencysystem.bean.UserBean
import com.permissionx.currencysystem.module.mine.DownLoadActivity
import com.permissionx.currencysystem.module.mine.OrderActivity
import com.permissionx.currencysystem.module.mine.UserInfoActivity
import com.permissionx.currencysystem.utils.ImageLoaderManager
import com.permissionx.currencysystem.utils.LoadingUtils
import kotlinx.android.synthetic.main.fragment_layout.*
import kotlinx.android.synthetic.main.fragment_mine_layout.*

class MineFragment :MvpFragment<MineContract.IPresenter>(),MineContract.IView{

    private var userEntity: UserBean? =null
    
    override fun getUserDataResult(data: UserBean?) {
        userEntity=data
//        data.name?.let { SPUtils.put("name", it) }
//        data.avatar?.let { SPUtils.put("avatarUrl", it) }
        activity?.let {
            ImageLoaderManager.loadCircleImage(activity,data?.avatar,iv_icon)
        }
        tv_name.text=data?.name?:"0"
        tv_level.text=data?.level?:"0"
        tv_bzz_power.text=data?.bzz_power?:"0"
        tv_point_hashrate.text=data?.fil_power?:"0"
        tv_earnings.text=data?.total_usdt?:"0"
    }

    override fun registerPresenter()=MinePresenter::class.java

    override fun showErrorMsg(msg: String) {
        Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        LoadingUtils.showLoading(activity)
    }

    override fun hideLoading() {
        LoadingUtils.hideLoading()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        LogUtils.e("onSaveInstanceState","MineFragment")
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtils.e("onCreateView","MineFragment")
        return activity?.let { LayoutInflater.from(it).inflate(R.layout.fragment_mine_layout,container,false) }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1 && resultCode==10){
            getPresenter().getUserData()
        }
    }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        LogUtils.e("onActivityCreated","MineFragment")
          getPresenter().getUserData()
            iv_icon.setOnClickListener {
                activity?.let {
                    val intent=Intent(activity,UserInfoActivity::class.java)

                    intent.putExtra("user",userEntity)
                    startActivityForResult(intent,1)
//                    startActivity(intent)
                }
            }
            tv_download.setOnClickListener {
                startActivity(Intent(activity,DownLoadActivity::class.java))
            }
            ll_order.setOnClickListener {
                startActivity(Intent(activity,OrderActivity::class.java))
            }
    }
}