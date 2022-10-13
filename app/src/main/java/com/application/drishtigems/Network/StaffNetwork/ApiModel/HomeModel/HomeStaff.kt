package com.application.drishtigems.Network.StaffNetwork.ApiModel.HomeModel

import com.google.gson.annotations.SerializedName

data class HomeStaff(

    @field:SerializedName("given_discount")
    val givenDiscount: Double,

    @field:SerializedName("user_targets")
    val userTargets: List<Any>,

    @field:SerializedName("given_target")
    val givenTarget: Double,

    @field:SerializedName("achived_target")
    val achivedTarget: Int,

    @field:SerializedName("no_of_team_members")
    val noOfTeamMembers: Int,

    @field:SerializedName("last_name")
    val lastName: String,

    @field:SerializedName("first_name")
    val firstName: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("incentive_amount")
    val incentiveAmount: Int,

    @field:SerializedName("pending_days_in_month")
    val pendingDaysInMonth: Int
)
