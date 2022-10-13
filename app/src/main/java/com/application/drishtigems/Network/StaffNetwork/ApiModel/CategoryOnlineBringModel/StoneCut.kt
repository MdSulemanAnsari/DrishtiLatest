package com.application.drishtigems.Network.StaffNetwork.ApiModel.CategoryOnlineBringModel


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class StoneCut(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)