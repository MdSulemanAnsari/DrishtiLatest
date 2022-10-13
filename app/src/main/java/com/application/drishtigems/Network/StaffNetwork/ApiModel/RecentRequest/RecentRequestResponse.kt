package com.application.drishtigems.Network.StaffNetwork.ApiModel.RecentRequest

import com.google.gson.annotations.SerializedName

data class RecentRequestResponse(

    @field:SerializedName("data")
    val data: List<DataItem>,

    @field:SerializedName("total_count")
    val totalCount: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int
)

data class AddressState(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)

data class TotalApprovedExpense(

    @field:SerializedName("price__sum")
    val priceSum: Any
)

data class GroupsItem(

    @field:SerializedName("permissions")
    val permissions: List<Int>,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)

data class AddressCity(

    @field:SerializedName("district")
    val district: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("state")
    val state: Int
)

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
    val userPermissions: List<Any>,

    @field:SerializedName("is_staff")
    val isStaff: Boolean,

    @field:SerializedName("last_login")
    val lastLogin: String,

    @field:SerializedName("address_state")
    val addressState: AddressState,

    @field:SerializedName("last_name")
    val lastName: String,

    @field:SerializedName("groups")
    val groups: List<GroupsItem>,

    @field:SerializedName("address_city")
    val addressCity: AddressCity,

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
    val gstNumber: Any,

    @field:SerializedName("first_name")
    val firstName: String,

    @field:SerializedName("address_district")
    val addressDistrict: Any,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("address_pin_code")
    val addressPinCode: Int,

    @field:SerializedName("supervisor")
    val supervisor: String
)

data class ApplicationStatus(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)

data class DataItem(

    @field:SerializedName("created_time")
    val createdTime: String,

    @field:SerializedName("price")
    val price: String,

    @field:SerializedName("total_approved_expense")
    val totalApprovedExpense: TotalApprovedExpense,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("application_status")
    val applicationStatus: ApplicationStatus,

    @field:SerializedName("On_Date_time")
    val onDateTime: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("type_of_expense")
    val typeOfExpense: String,

    @field:SerializedName("user")
    val user: User
)
