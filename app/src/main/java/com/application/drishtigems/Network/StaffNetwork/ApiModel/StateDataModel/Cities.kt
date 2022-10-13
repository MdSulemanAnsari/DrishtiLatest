package com.application.drishtigems.Network.StaffNetwork.ApiModel.StateDataModel


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class Cities(
    @SerializedName("district")
    @Expose
    val district: Int,

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("state")
    @Expose
    val state: Int
)