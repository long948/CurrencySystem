package com.permissionx.currencysystem.bean

data class InvitationBean(val list:List<InvitationList>,val page :Page)
data class InvitationList(val mobile:String,val team_power:String)
data class Page(val total :Int)