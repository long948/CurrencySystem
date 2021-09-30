package com.permissionx.currencysystem.module.mine


import android.graphics.Color
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.ToastUtils
import com.block.xjfkchain.ui.fragment.MyFragmentPagerAdapter
import com.google.android.material.tabs.TabLayout

import com.permissionx.currencysystem.R
import com.permissionx.currencysystem.base.BaseMvpActivity
import com.permissionx.currencysystem.bean.OrderEntity
import com.permissionx.currencysystem.fragment.HomeFragment
import com.permissionx.currencysystem.utils.LoadingUtils
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity :BaseMvpActivity<OrderContract.IPresenter>(),OrderContract.IView {
    override fun getDataResult(data: List<OrderEntity>) {

    }



    override fun getLayoutId()=R.layout.activity_order
    lateinit  var mTitleList :ArrayList<String>
    lateinit var mFragments:ArrayList<Fragment>

    override fun registerPresenter()=OrderPresenter::class.java

    override fun showErrorMsg(msg: String) {
        ToastUtils.showShort(msg)
    }

    override fun showLoading() {
      LoadingUtils.showLoading(this)
    }

    override fun hideLoading() {
       LoadingUtils.hideLoading()
    }

    override fun initView() {
            order_topBar.setTitle("我的订单")
            order_topBar.addLeftBackImageButton().setOnClickListener {  finish()}
            initFragmentList()
            val myFragmentPagerAdapter=MyFragmentPagerAdapter(supportFragmentManager,mFragments,mTitleList)
            vp_type.adapter=myFragmentPagerAdapter
            myFragmentPagerAdapter.notifyDataSetChanged()
//            tab_gank.tabMode=TabLayout.MODE_FIXED
            tab_gank.setupWithViewPager(vp_type)
            tab_gank.setTabTextColors(Color.BLACK,Color.RED)
    }

    private fun initFragmentList() {

        mTitleList= ArrayList(4)
        mFragments= ArrayList(4)
        mTitleList.clear()
        mTitleList.add("全部")
        mTitleList.add("未支付")
        mTitleList.add("审核")
        mTitleList.add("进行中")
        mTitleList.add("已完成")
        mFragments.add(OrderFragment.newInstance(OrderFragment.STATUS_ALL))
        mFragments.add(OrderFragment.newInstance(OrderFragment.STATUS_NOPAY_STR))
        mFragments.add(OrderFragment.newInstance(OrderFragment.STATUS_CHECK_STR))
        mFragments.add(OrderFragment.newInstance(OrderFragment.STATUS_PROCEED_STR))
        mFragments.add(OrderFragment.newInstance(OrderFragment.STATUS_COMPLETE_STR))
    }

}
