package com.application.drishtigems.Network.StaffNetwork.ApiModel.EditProfileModel


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class EditProfileDataClass(
    @SerializedName("address")
    val address: String,
    @SerializedName("address_city")
    val addressCity: AddressCity,
    @SerializedName("address_district")
    val addressDistrict: Any,
    @SerializedName("address_pin_code")
    val addressPinCode: Int,
    @SerializedName("address_state")
    val addressState: AddressState,
    @SerializedName("city")
    val city: Any,
    @SerializedName("date_joined")
    val dateJoined: String,
    @SerializedName("district")
    val district: Any,
    @SerializedName("dob")
    val dob: String,
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
    @SerializedName("gst_number")
    val gstNumber: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_staff")
    val isStaff: Boolean,
    @SerializedName("is_superuser")
    val isSuperuser: Boolean,
    @SerializedName("last_login")
    val lastLogin: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("profile_status")
    val profileStatus: Int,
    @SerializedName("state")
    val state: Any,
    @SerializedName("user_permissions")
    val userPermissions: List<Any>,
    @SerializedName("user_status")
    val userStatus: Int,
    @SerializedName("user_targets")
    val userTargets: List<Any>
)