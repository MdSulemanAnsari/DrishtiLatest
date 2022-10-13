package com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller

import com.google.gson.annotations.SerializedName

data class AddtoWishListModel(

    @field:SerializedName("data")
    val data: Data,

    @field:SerializedName("get_wishlist_count")
    val getWishlistCount: Int
)

data class Data(

    @field:SerializedName("created_time")
    val createdTime: String,

    @field:SerializedName("certification_type")
    val certificationType: Any,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("user")
    val user: Int,

    @field:SerializedName("gem")
    val gem: Int,

    @field:SerializedName("jewellery_type")
    val jewelleryType: Any
)
