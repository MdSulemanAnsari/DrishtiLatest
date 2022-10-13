package com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller.MyProfileVendor

import com.google.gson.annotations.SerializedName

data class GetWishListNewResp(

    @field:SerializedName("data")
    val data: List<DataItem>,

    @field:SerializedName("total_count")
    val totalCount: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int
)

data class DataItem(

    @field:SerializedName("added_to_wishlist")
    val addedToWishlist: Boolean,

    @field:SerializedName("weight_unit")
    val weightUnit: WeightUnit,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("user")
    val user: User,

    @field:SerializedName("gem")
    val gem: Gem,

    @field:SerializedName("added_to_cart")
    val addedToCart: Boolean
)

data class GemImagesItem(

    @field:SerializedName("image_path")
    val imagePath: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("gem")
    val gem: Int,

    @field:SerializedName("status")
    val status: Int
)

data class User(

    @field:SerializedName("given_target")
    val givenTarget: Double,

    @field:SerializedName("user_permissions")
    val userPermissions: List<Any>,

    @field:SerializedName("city")
    val city: Any,

    @field:SerializedName("no_of_team_members")
    val noOfTeamMembers: Int,

    @field:SerializedName("address_state")
    val addressState: Int,

    @field:SerializedName("given_discount")
    val givenDiscount: Double,

    @field:SerializedName("profile_status")
    val profileStatus: Int,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("date_joined")
    val dateJoined: String,

    @field:SerializedName("state")
    val state: Any,

    @field:SerializedName("first_name")
    val firstName: String,

    @field:SerializedName("address_district")
    val addressDistrict: Any,

    @field:SerializedName("email")
    val email: String,

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

    @field:SerializedName("is_staff")
    val isStaff: Boolean,

    @field:SerializedName("last_login")
    val lastLogin: String,

    @field:SerializedName("last_name")
    val lastName: String,

    @field:SerializedName("groups")
    val groups: List<Int>,

    @field:SerializedName("address_city")
    val addressCity: Int,

    @field:SerializedName("user_targets")
    val userTargets: List<Any>,

    @field:SerializedName("achived_target")
    val achivedTarget: Int,

    @field:SerializedName("dob")
    val dob: String,

    @field:SerializedName("district")
    val district: Any,

    @field:SerializedName("phone_number")
    val phoneNumber: String,

    @field:SerializedName("gst_number")
    val gstNumber: Any,

    @field:SerializedName("supervisor")
    val supervisor: Any,

    @field:SerializedName("address_pin_code")
    val addressPinCode: Int
)

data class Gem(

    @field:SerializedName("added_to_wishlist")
    val addedToWishlist: Boolean,

    @field:SerializedName("dimension_height")
    val dimensionHeight: Double,

    @field:SerializedName("cart_count")
    val cartCount: Int,

    @field:SerializedName("origin")
    val origin: Int,

    @field:SerializedName("sold_to_user")
    val soldToUser: SoldToUser,

    @field:SerializedName("is_sold")
    val isSold: Boolean,

    @field:SerializedName("price")
    val price: String,

    @field:SerializedName("stone_quality")
    val stoneQuality: Int,

    @field:SerializedName("category_color")
    val categoryColor: Int,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("sku")
    val sku: String,

    @field:SerializedName("barcode")
    val barcode: String,

    @field:SerializedName("gem_images")
    val gemImages: List<Any>,

    @field:SerializedName("stone_cut")
    val stoneCut: Int,

    @field:SerializedName("shape")
    val shape: Int,

    @field:SerializedName("gem_videos")
    val gemVideos: List<Any>,

    @field:SerializedName("dimension_width")
    val dimensionWidth: Double,

    @field:SerializedName("dimension_length")
    val dimensionLength: Double,

    @field:SerializedName("weight")
    val weight: Double,

    @field:SerializedName("certification_type")
    val certificationType: Any,

    @field:SerializedName("added_to_cart")
    val addedToCart: Boolean,

    @field:SerializedName("weight_unit")
    val weightUnit: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("comment")
    val comment: String,

    @field:SerializedName("category")
    val category: Int
)

data class WeightUnit(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)

data class GemVideosItem(

    @field:SerializedName("video_path")
    val videoPath: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("video_thumbnail_path")
    val videoThumbnailPath: String,

    @field:SerializedName("gem")
    val gem: Int,

    @field:SerializedName("status")
    val status: Int
)

data class SoldToUser(

    @field:SerializedName("given_target")
    val givenTarget: Double,

    @field:SerializedName("user_permissions")
    val userPermissions: List<Any>,

    @field:SerializedName("city")
    val city: Any,

    @field:SerializedName("no_of_team_members")
    val noOfTeamMembers: Int,

    @field:SerializedName("address_state")
    val addressState: Int,

    @field:SerializedName("given_discount")
    val givenDiscount: Double,

    @field:SerializedName("profile_status")
    val profileStatus: Int,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("date_joined")
    val dateJoined: String,

    @field:SerializedName("state")
    val state: Any,

    @field:SerializedName("first_name")
    val firstName: String,

    @field:SerializedName("address_district")
    val addressDistrict: Any,

    @field:SerializedName("email")
    val email: String,

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

    @field:SerializedName("is_staff")
    val isStaff: Boolean,

    @field:SerializedName("last_login")
    val lastLogin: String,

    @field:SerializedName("last_name")
    val lastName: String,

    @field:SerializedName("groups")
    val groups: List<Int>,

    @field:SerializedName("address_city")
    val addressCity: Int,

    @field:SerializedName("user_targets")
    val userTargets: List<Any>,

    @field:SerializedName("achived_target")
    val achivedTarget: Int,

    @field:SerializedName("dob")
    val dob: String,

    @field:SerializedName("district")
    val district: Any,

    @field:SerializedName("phone_number")
    val phoneNumber: String,

    @field:SerializedName("gst_number")
    val gstNumber: Any,

    @field:SerializedName("supervisor")
    val supervisor: Any,

    @field:SerializedName("address_pin_code")
    val addressPinCode: Int
)
