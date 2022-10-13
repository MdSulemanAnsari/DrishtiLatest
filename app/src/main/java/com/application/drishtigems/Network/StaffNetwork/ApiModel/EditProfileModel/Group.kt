package com.application.drishtigems.Network.StaffNetwork.ApiModel.EditProfileModel


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Group(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("permissions")
    val permissions: List<Int>
)