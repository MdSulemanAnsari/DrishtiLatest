package com.application.drishtigems.Network.StaffNetwork.ApiModel.CategoryOnlineBringModel


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CategoryColor(
    @SerializedName("category")
    val category: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)