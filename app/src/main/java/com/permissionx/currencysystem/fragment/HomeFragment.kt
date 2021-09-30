package com.permissionx.currencysystem.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hankkin.library.mvp.view.MvpFragment
import com.permissionx.currencysystem.R
import com.permissionx.currencysystem.bean.InvitationList
import com.permissionx.currencysystem.utils.LoadingUtils
import kotlinx.android.synthetic.main.fragment_home_layout.*
import kotlinx.android.synthetic.main.fragment_layout.*
import kotlinx.android.synthetic.main.home_item.view.*

class HomeFragment :MvpFragment<HomeContract.IPresenter>(),HomeContract.IView{
    private lateinit var mBuffAdapter: BuffAdapter
    override fun registerPresenter()=HomePresenter::class.java

    override fun showErrorMsg(msg: String) {
        activity?.let {
            Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show()
        }
    }


    override fun invitationListResult(data: List<InvitationList>) {
        mBuffAdapter= BuffAdapter(data)
        home_fg_recyclerView.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=mBuffAdapter
        }
        home_fg_refresh.isRefreshing=false

    }

    override fun showLoading() {
        LoadingUtils.showLoading(activity)
    }

    override fun hideLoading() {
        LoadingUtils.hideLoading()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(activity).inflate(R.layout.fragment_home_layout,container,false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
            getPresenter().invitationList()
        home_fg_refresh.setOnRefreshListener {
            getPresenter().invitationList()
        }
    }

    class BuffAdapter(val data: List<InvitationList>):
        RecyclerView.Adapter<BuffAdapter.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).
                inflate(R.layout.home_item,parent,false))
        }

        override fun getItemCount()=data.size



        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val invitationList=data[position]
            holder.hometxt1.text=invitationList.mobile
            holder.hometxt2.text=invitationList.team_power
        }

        inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
            val hometxt1=view.home_item1
            val hometxt2=view.home_item2
        }
    }
}