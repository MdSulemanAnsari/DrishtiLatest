package com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller
import com.google.gson.annotations.SerializedName

data class GemVideosItem(

    @field:SerializedName("image_path")
    val imagePath: String,

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
