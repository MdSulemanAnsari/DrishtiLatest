package com.application.drishtigems.Network

import com.application.drishtigems.Network.StaffNetwork.ApiModel.AddTocartModel.AddCartNew
import com.application.drishtigems.Network.StaffNetwork.ApiModel.CategoryOnlineBringModel.CategoryOnlineBringModel
import com.application.drishtigems.Network.StaffNetwork.ApiModel.CustomOrder.CustomOrderModelNew
import com.application.drishtigems.Network.StaffNetwork.ApiModel.CustomOrder.CustomOrderRecentModel
import com.application.drishtigems.Network.StaffNetwork.ApiModel.CustomOrder.SpinnerCustomOrder
import com.application.drishtigems.Network.StaffNetwork.ApiModel.EditProfileModel.EditProfileDataClass
import com.application.drishtigems.Network.StaffNetwork.ApiModel.HomeModel.HomeStaff
import com.application.drishtigems.Network.StaffNetwork.ApiModel.LoginUserModel.UserModel
import com.application.drishtigems.Network.StaffNetwork.ApiModel.MyProfileModel.MyProfileModel
import com.application.drishtigems.Network.StaffNetwork.ApiModel.RecentRequest.RecentRequestResponse
import com.application.drishtigems.Network.StaffNetwork.ApiModel.ShoppingCartModel.ShoppingCartDeleteResponse
import com.application.drishtigems.Network.StaffNetwork.ApiModel.StateDataModel.ApiDataModel
import com.application.drishtigems.Network.StaffNetwork.ApiModel.StateDataModel.Cities
import com.application.drishtigems.Network.StaffNetwork.ApiModel.natural.NaturalResponse
import com.application.drishtigems.Network.StaffNetwork.ApiModel.similarProductsResponse.SimilarProductsResponse
import com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller.AddtoWishListModel
import com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller.BuyNowCartModel
import com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller.MyProfileVendor.GetWishListNewResp
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("get_states_districts_cities_list")
    fun getStates(): Call<ApiDataModel>

    @GET("get_city_list_by_state/{id}")
    fun getCities(@Path("id") id: Int?):Call<ArrayList<Cities>>

    @FormUrlEncoded
    @POST("sub_admin_login/")
    fun loginPost(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("phone") phone_number: String,
    ):Call<UserModel>

    @Multipart
    @PUT("update_profile/")
    fun editProfile(
        @Header("Authorization") token: String?,
        @PartMap hashMap: HashMap<String, RequestBody?>,
        @Part pictureGalley: MultipartBody.Part?,
    ):Call<EditProfileDataClass>

    @GET("get_user")
    fun myProfileGet(
        @Header("Authorization") token: String?, ): Call<MyProfileModel>

    @GET("categories/")
    fun myBringStockGet(@Header("Authorization") token: String?): Call<CategoryOnlineBringModel>

    @GET("gems/")
    fun gemsGetList(  @Header("Authorization") token: String?,
                      @QueryMap hasMap: HashMap<String, Any>):Call<NaturalResponse>

    @GET("gems/{id}")
    fun getFilterData(  @Header("Authorization") token: String?,
        @Path("id") id: String?):Call<AddCartNew>

    @GET("get_suggested_gems/")
    fun similarProduct(  @Header("Authorization") token: String?,@QueryMap hasMap: HashMap<String, String>):Call<SimilarProductsResponse>

    @GET("get_staff_dashboard_data/")
    fun getTargets(
        @Header("Authorization") token: String?,):Call<HomeStaff>

    //Jeweller Api

    @FormUrlEncoded
    @POST("user_wishlist/")
    fun addToWishlist(
        @Header("Authorization") token: String?,
        @Field("gem") id:Int):Call<AddtoWishListModel>

    @GET("user_wishlist/")
    fun getWishlist(
        @Query("page")page:Int?,
        @Header("Authorization") token: String?):Call<GetWishListNewResp>

    @DELETE("remove_from_wishlist_by_gem_id/{id}")
    fun removeWishlist(
        @Header("Authorization") token: String?,
        @Path("id") id: Int):Call<JsonObject>

    @FormUrlEncoded
    @POST("contact_us/")
    fun contactUs(
        @Header("Authorization") token: String?,
        @FieldMap map: java.util.HashMap<String, String?>):Call<JsonObject>

    @FormUrlEncoded
    @POST("change_password/")
    fun changePassword(
        @Header("Authorization") token: String?,
        @FieldMap map: java.util.HashMap<String, String?>):Call<JsonObject>

    @Multipart
    @POST("extra_expenses/")
    fun taDaForm(
        @Header("Authorization") token: String?,
        @PartMap hashMap: HashMap<String, RequestBody?>
    ):Call<JsonObject>

    @GET("extra_expenses/")
    fun recentRequest(
        @Query("page")pageNo: Int,
        @Header("Authorization") token: String?):Call<RecentRequestResponse>

    @GET("get_data_for_custom_order/")
    fun getCustomOrderSpinnerData(
        @Header("Authorization") token: String?):Call<SpinnerCustomOrder>


    @Headers("Content-Type: application/json")
    @POST("custom_order/")
    fun sendCustomOrderData(
        @Header("Authorization") token: String?,
        @Body obj : CustomOrderModelNew):Call<JsonObject>

    @GET("custom_order/")
    fun getCustomOrders( @Header("Authorization") token: String?,):Call<ArrayList<CustomOrderRecentModel>>

    @Headers("Content-Type: application/json")
    @POST("user_cart/")
    fun addToCart(
        @Header("Authorization") token: String?,
        @Body obj: BuyNowCartModel):Call<JsonObject>

    @GET("user_cart/")
    fun getShoppingCart( @Header("Authorization") token: String?):Call<ArrayList<ShoppingCartDeleteResponse>>


    @DELETE("remove_from_cart_by_gem_id/{gem_id}")
    fun removeFromCart( @Header("Authorization") token: String?
                        ,@Path("gem_id") gem_id: Int
    ):Call<JsonObject>


}