package com.application.drishtigems.Network.StaffNetwork.ApiModel.LoginUserModel


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class UserModel(
    @SerializedName("address")
    val address: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("given_discount")
    val givenDiscount: Double,
    @SerializedName("given_target")
    val givenTarget: Double,
    @SerializedName("groups")
    val groups: List<Group>,
    @SerializedName("image")
    val image: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("user_targets")
    val userTargets: List<Any>
)