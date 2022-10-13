package com.application.drishtigems.Network.StaffNetwork.ApiModel.CustomOrder

import com.google.gson.annotations.SerializedName

data class SpinnerCustomOrder(

    @field:SerializedName("stone_quality_list")
    val stoneQualityList: List<StoneQualityListItem>,

    @field:SerializedName("weight_list")
    val weightList: List<WeightListItem>
)

data class WeightListItem(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int

)
data class StoneQualityListItem(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)
