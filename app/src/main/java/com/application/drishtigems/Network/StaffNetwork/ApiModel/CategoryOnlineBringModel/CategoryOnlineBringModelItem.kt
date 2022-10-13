package com.application.drishtigems.Network.StaffNetwork.ApiModel.CategoryOnlineBringModel


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CategoryOnlineBringModelItem(
    @SerializedName("birefringence")
    val birefringence: String,
    @SerializedName("cart_count")
    val cartCount: Int,
    @SerializedName("category_colors")
    val categoryColors: List<CategoryColor>,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("gst")
    val gst: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("magnification")
    val magnification: String,
    @SerializedName("optic_character")
    val opticCharacter: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("referactive_index")
    val referactiveIndex: String,
    @SerializedName("shape")
    val shape: Shape,
    @SerializedName("specific_gravity")
    val specificGravity: String,
    @SerializedName("stone_cut")
    val stoneCut: StoneCut,
    @SerializedName("stone_quality")
    val stoneQuality: StoneQuality,
    @SerializedName("title")
    val title: String,
    @SerializedName("unique_number_prefix")
    val uniqueNumberPrefix: String
)