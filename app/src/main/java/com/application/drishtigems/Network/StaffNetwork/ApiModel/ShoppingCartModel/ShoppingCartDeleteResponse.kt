package com.application.drishtigems.Network.StaffNetwork.ApiModel.ShoppingCartModel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ShoppingCartDeleteResponse(
    val addedToWishlist: Boolean,
    val createdTime: String,
    val expectedDeliveryTime: String,
    val certificationType: Any,
    val id: Int,
    val user: User,
    val gem: Gem,
    val jewelleryType: Any,
    var addedToCart: Boolean
)

data class GemImagesItem(
    val imagePath: String,
    val id: Int,
    val gem: Int,
    val status: Int
)

data class CategoryColor(
    val name: String,
    val id: Int,
    val category: Category1
)

data class GemVideosItem(
    val videoPath: String,
    val id: Int,
    val videoThumbnailPath: String,
    val gem: Int,
    val status: Int
)

data class StoneQuality(
    val name: String,
    val id: Int
)


data class Category(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("stone_cut")
    val stoneCut: StoneCut,

    @field:SerializedName("magnification")
    val magnification: String,

    @field:SerializedName("optic_character")
    val opticCharacter: String,

    @field:SerializedName("shape")
    val shape: Shape,

    @field:SerializedName("gst")
    val gst: Double,

    @field:SerializedName("specific_gravity")
    val specificGravity: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("referactive_index")
    val referactiveIndex: String,

    @field:SerializedName("unique_number_prefix")
    val uniqueNumberPrefix: String,

    @field:SerializedName("price")
    val price: String,

    @field:SerializedName("stone_quality")
    val stoneQuality: StoneQuality,

    @field:SerializedName("birefringence")
    val birefringence: String,

    @field:SerializedName("comment")
    val comment: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("optic_c     haracter")
    val opticCHaracter: String
)
data class Category1(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("stone_cut")
    val stoneCut: Int,

    @field:SerializedName("magnification")
    val magnification: String,

    @field:SerializedName("optic_character")
    val opticCharacter: String,

    @field:SerializedName("shape")
    val shape: Int,

    @field:SerializedName("gst")
    val gst: Double,

    @field:SerializedName("specific_gravity")
    val specificGravity: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("referactive_index")
    val referactiveIndex: String,

    @field:SerializedName("unique_number_prefix")
    val uniqueNumberPrefix: String,

    @field:SerializedName("price")
    val price: String,

    @field:SerializedName("stone_quality")
    val stoneQuality: Int,

    @field:SerializedName("birefringence")
    val birefringence: String,

    @field:SerializedName("comment")
    val comment: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("optic_c     haracter")
    val opticCHaracter: String
)


data class Shape(
    val name: String,
    val id: Int
)

data class CertificationType(
    val name: String,
    val id: Int
)

data class User(
    val image: String,
    val userStatus: Int,
    val isSuperuser: Boolean,
    val isActive: Boolean,
    val address: String,
    val userPermissions: List<Any>,
    val isStaff: Boolean,
    val lastLogin: String,
    val addressState: AddressState,
    val lastName: String,
    val groups: List<GroupsItem>,
    val addressCity: AddressCity,
    val profileStatus: Int,
    val password: String,
    val dob: String,
    val phoneNumber: String,
    val id: Int,
    val dateJoined: String,
    val gstNumber: Any,
    val firstName: String,
    val addressDistrict: Any,
    val email: String,
    val addressPinCode: Int,
    val supervisor: String
)

data class GroupsItem(
    val permissions: List<Int>,
    val name: String,
    val id: Int
)

data class StoneCut(
    val name: String,
    val id: Int
)

data class WeightUnit(
    val name: String,
    val id: Int
)

data class Origin(
    val name: String,
    val id: Int
)

data class SoldToUser(
    val givenTarget: Double,
    val userPermissions: List<Any>,
    val noOfTeamMembers: Int,
    val addressState: Int,
    val givenDiscount: Double,
    val profileStatus: Int,
    val password: String,
    val userAssignedAddress: List<Any>,
    val id: Int,
    val dateJoined: String,
    val firstName: String,
    val addressDistrict: Any,
    val email: String,
    val image: String,
    val userStatus: Int,
    val isSuperuser: Boolean,
    val isActive: Boolean,
    val address: String,
    val isStaff: Boolean,
    val lastLogin: String,
    val lastName: String,
    val groups: List<Int>,
    val addressCity: Int,
    val userTargets: List<Any>,
    val achivedTarget: Int,
    val dob: String,
    val phoneNumber: String,
    val gstNumber: Any,
    val supervisor: Any,
    val addressPinCode: Int
)

data class AddressState(
    val name: String,
    val id: Int
)

data class AddressCity(
    val district: Int,
    val name: String,
    val id: Int,
    val state: Int
)

data class Gem(
    val addedToWishlist: Boolean,
    val dimensionHeight: Double,
    val cartCount: Int,
    val origin: Origin,
    val soldToUser: SoldToUser,
    val isSold: Boolean,
    val price: String,
    val stoneQuality: StoneQuality,
    val categoryColor: CategoryColor,
    val id: Int,
    val sku: String,
    val barcode: String,
    val gemImages: List<GemImagesItem>,
    val stoneCut: StoneCut,
    val shape: Shape,
    val gemVideos: List<GemVideosItem>,
    val dimensionWidth: Double,
    val dimensionLength: Double,
    val weight: Double,
    val certificationType: CertificationType,
    val addedToCart: Boolean,
    val weightUnit: WeightUnit,
    val name: String,
    val comment: String,
    val category: Category,
    val status: Int
)

