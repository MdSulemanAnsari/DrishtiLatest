package com.application.drishtigems.Network.StaffNetwork.ApiModel.StateDataModel


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class ApiDataModel(
    @SerializedName("cities_list")
    @Expose
    val citiesList: List<Cities>,

    @SerializedName("districts_list")
    @Expose
    val districtsList: List<Districts>,

    @SerializedName("states_list")
    @Expose
    val statesList: List<States>
)
