package com.application.drishtigems.Network.StaffNetwork.ApiModel.StateDataModel


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class States(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)