package com.permissionx.currencysystem.module.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.hankkin.library.mvp.view.MvpFragment
import com.permissionx.currencysystem.R
import com.permissionx.currencysystem.adapter.OrderAdapter
import com.permissionx.currencysystem.bean.OrderEntity
import com.permissionx.currencysystem.utils.LoadingUtils
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment :MvpFragment<OrderContract.IPresenter>(),OrderContract.IView{
    companion object{
        const       val  STATUS_ALL = "all"
        const       val STATUS_NOPAY_STR = "wait"
        const       val STATUS_CHECK_STR = "waitconfirm"
        const       val STATUS_PROCEED_STR = "runing"
        const       val STATUS_COMPLETE_STR = "complete"


        fun newInstance(orderType:String):OrderFragment{
            val orderFragment=OrderFragment()
            val bundle=Bundle()
            bundle.putString("type",orderType)
            orderFragment.arguments=bundle
            return  orderFragment
        }
    }

    private var mOrderType=""
    lateinit var mOrderAdapter:OrderAdapter
    private var mPageNo=1


    override fun getDataResult(data: List<OrderEntity>) {
        if (data.isNotEmpty()){
        mOrderAdapter= OrderAdapter(data)
        order_recyclerView.layoutManager=LinearLayoutManager(activity)
        order_recyclerView.adapter=mOrderAdapter
        mOrderAdapter.notifyDataSetChanged()}
        order_refresh.isRefreshing=false
    }


    override fun registerPresenter()=OrderPresenter::class.java

    override fun showErrorMsg(msg: String) {
     ToastUtils.showShort(msg)
    }

    override fun showLoading() {
        activity?.let {  LoadingUtils.showLoading(activity)}

    }

    override fun hideLoading() {
        activity?.let { LoadingUtils.hideLoading() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPageNo=1
        initView()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(activity).inflate(R.layout.fragment_order,container,false)
    }
    private fun initView() {
        mOrderType=arguments?.getString("type")?:""

        getPresenter().getData(mPageNo.toString(),mOrderType)
        order_refresh.setOnRefreshListener {
            mPageNo++
            getPresenter().getData( mPageNo.toString(),mOrderType)
        }
    }


}