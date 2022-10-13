package com.application.drishtigems.Network.StaffNetwork.ApiModel.CustomOrder

import android.os.Parcelable
import com.application.drishtigems.Network.StaffNetwork.ApiModel.RecentRequest.DataItem
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CustomOrderStatus(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
) : Parcelable

@Parcelize
data class WeightUnit(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
) : Parcelable

@Parcelize
data class StoneQuality(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
) : Parcelable

@Parcelize
data class User(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("user_status")
    val userStatus: Int,

    @field:SerializedName("is_superuser")
    val isSuperuser: Boolean,

    @field:SerializedName("is_active")
    val isActive: Boolean,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("user_permissions")
    val userPermissions: List<String>,

    @field:SerializedName("is_staff")
    val isStaff: Boolean,

    @field:SerializedName("last_login")
    val lastLogin: String,

    @field:SerializedName("address_state")
    val addressState: Int,

    @field:SerializedName("last_name")
    val lastName: String,

    @field:SerializedName("groups")
    val groups: List<Int>,

    @field:SerializedName("address_city")
    val addressCity: Int,

    @field:SerializedName("profile_status")
    val profileStatus: Int,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("dob")
    val dob: String,

    @field:SerializedName("phone_number")
    val phoneNumber: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("date_joined")
    val dateJoined: String,

    @field:SerializedName("gst_number")
    val gstNumber: String,

    @field:SerializedName("first_name")
    val firstName: String,

    @field:SerializedName("address_district")
    val addressDistrict: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("address_pin_code")
    val addressPinCode: Int,

    @field:SerializedName("supervisor")
    val supervisor: String
) : Parcelable

@Parcelize
data class CustomOrderRecentModel(

    @field:SerializedName("created_time")
    val createdTime: String,

    @field:SerializedName("quantity")
    val quantity: Int,

    @field:SerializedName("max_price")
    val maxPrice: String,

    @field:SerializedName("min_price")
    val minPrice: String,

    @field:SerializedName("weight_unit")
    val weightUnit: WeightUnit,

    @field:SerializedName("stone_quality")
    val stoneQuality: StoneQuality,

    @field:SerializedName("gem_name")
    val gemName: String,

    @field:SerializedName("weight")
    val weight: Double,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("custom_order_status")
    val customOrderStatus: CustomOrderStatus,

    @field:SerializedName("user")
    val user: User
) : Parcelable