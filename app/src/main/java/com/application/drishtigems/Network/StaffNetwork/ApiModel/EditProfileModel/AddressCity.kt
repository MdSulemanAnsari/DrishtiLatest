package com.application.drishtigems.Network.StaffNetwork.ApiModel.EditProfileModel


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AddressCity(
    @SerializedName("district")
    val district: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("state")
    val state: Int
)