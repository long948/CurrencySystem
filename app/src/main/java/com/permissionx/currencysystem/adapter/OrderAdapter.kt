package com.permissionx.currencysystem.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.permissionx.currencysystem.R
import com.permissionx.currencysystem.bean.OrderEntity
import com.permissionx.currencysystem.utils.ImageLoaderManager
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.fragment_mine_layout.*
import kotlinx.android.synthetic.main.item_order.view.*


class OrderAdapter(val data:List<OrderEntity>):RecyclerView.Adapter<OrderAdapter.ViewHolder>(){
    lateinit var mContext:Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        mContext=parent.context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_order,parent,false))
    }

    override fun getItemCount()=data.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=data[position]
        ImageLoaderManager.loadRoundImage(mContext,item.product_cover,holder.iv_image,8)
//        Glide.with(mContext).load(item.product_cover).into(holder.iv_image)
        holder.tv_name.text=item.product_name
        holder.tv_status.text=item.status_txt
        holder.tv_service_time.text="服务期：" + item.duration + "年"
        holder.tv_hashrate.text="算力："+if ( TextUtils.isEmpty(item.power))0 else item.power+"T"
        holder.tv_num.text=item.num + ""
        holder.tv_price_unit.text=item.price_unit + ""
        holder.tv_money.text=item.total + ""
    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val iv_image=view.iv_image
        val tv_name=view.tv_name
        val tv_status=view.tv_status
        val tv_service_time=view.tv_service_time
        val tv_hashrate=view.tv_hashrate
        val tv_num=view.tv_num
        val tv_price_unit=view.tv_price_unit
        val tv_money=view.tv_money
    }

}