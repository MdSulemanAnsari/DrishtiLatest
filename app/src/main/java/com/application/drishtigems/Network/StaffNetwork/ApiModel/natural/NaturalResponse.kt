package com.application.drishtigems.Network.StaffNetwork.ApiModel.natural

import com.google.gson.annotations.SerializedName

data class NaturalResponse(

    @field:SerializedName("data")
    val data: List<DataItem>,

    @field:SerializedName("total_count")
    val totalCount: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int
)

data class Shape(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)

data class CategoryColor(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("category")
    val category: Int
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

data class StoneQuality(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
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

data class CertificationType(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)

data class StoneCut(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)

data class Category(

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
    val id: Int
)

data class Origin(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)

data class DataItem(

    @field:SerializedName("added_to_wishlist")
    var addedToWishlist: Boolean,

    @field:SerializedName("dimension_height")
    val dimensionHeight: Double,

    @field:SerializedName("cart_count")
    var cartCount: Int,

    @field:SerializedName("origin")
    val origin: Origin,

    @field:SerializedName("is_sold")
    val isSold: Boolean,

    @field:SerializedName("price")
    val price: String,

    @field:SerializedName("stone_quality")
    val stoneQuality: Any,

    @field:SerializedName("category_color")
    val categoryColor: CategoryColor,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("sku")
    val sku: String,

    @field:SerializedName("barcode")
    val barcode: String,

    @field:SerializedName("gem_images")
    val gemImages: List<GemImagesItem>,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("stone_cut")
    val stoneCut: StoneCut,

    @field:SerializedName("shape")
    val shape: Shape,

    @field:SerializedName("gem_videos")
    val gemVideos: List<GemVideosItem>,

    @field:SerializedName("dimension_width")
    val dimensionWidth: Double,

    @field:SerializedName("dimension_length")
    val dimensionLength: Double,

    @field:SerializedName("weight")
    val weight: Double,

    @field:SerializedName("certification_type")
    val certificationType: CertificationType,

    @field:SerializedName("added_to_cart")
    var addedToCart: Boolean,

    @field:SerializedName("weight_unit")
    val weightUnit: WeightUnit,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("comment")
    val comment: String,

    @field:SerializedName("category")
    val category: Category
)

data class WeightUnit(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)
