package com.application.drishtigems.Network.StaffNetwork.ApiModel.MyProfileModel


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AddressState(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)