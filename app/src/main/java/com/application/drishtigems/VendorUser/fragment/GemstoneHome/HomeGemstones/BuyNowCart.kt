package com.application.drishtigems.VendorUser.fragment.GemstoneHome.HomeGemstones

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorAdapter.BringOnlineStockAdapter.DesignAdapter
import com.application.drishtigems.VendorUser.VendorAdapter.BringOnlineStockAdapter.SimilarProductVendorAdapter
import com.application.drishtigems.VendorUser.VendorAdapter.BringOnlineStockAdapter.ViewPagerBuyNowAdapter
import com.application.drishtigems.VendorUser.VendorModel.DesignModel
import com.application.drishtigems.VendorUser.fragment.ShoppingCartVendor
import com.application.drishtigems.databinding.FragmentBuyNowCartBinding
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.application.drishtigems.Network.RetrofitInstance
import com.application.drishtigems.Network.StaffNetwork.ApiModel.AddTocartModel.AddCartNew
import com.application.drishtigems.Network.StaffNetwork.ApiModel.ShoppingCartModel.ShoppingCartDeleteResponse
import com.application.drishtigems.Network.StaffNetwork.ApiModel.similarProductsResponse.DataItem
import com.application.drishtigems.Network.StaffNetwork.ApiModel.similarProductsResponse.SimilarProductsResponse
import com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller.AddtoWishListModel
import com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller.BuyNowCartModel
import com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller.GemVideosItem
import com.application.drishtigems.VendorUser.fragment.GemstoneHome.HomeGemstones.NaturalEmeraldStockVendor.Companion.category_id_vendor
import com.application.drishtigems.prefs.AppPrefs
import com.application.drishtigems.ui.activity.MainActivity
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuyNowCart : Fragment() {
    lateinit var binding: FragmentBuyNowCartBinding
    private var viewPagerBuyNowAdapter: ViewPagerBuyNowAdapter? = null
    var viewCaratListVendor: AddCartNew? = null
    var imageListGemsVendor: ArrayList<GemVideosItem> = ArrayList()
    var videoListGemsVendor: ArrayList<GemVideosItem> = ArrayList()
    var mergeListJeweller: ArrayList<GemVideosItem> = ArrayList()
   var buyNowCartModel=BuyNowCartModel()
    var similarProductVendorAdapter: SimilarProductVendorAdapter? = null
    private var dataSimilarListVendor: ArrayList<DataItem> = ArrayList()
    var hashMap: HashMap<String, String> = HashMap()

    var designAdapter: DesignAdapter? = null
    var listRingItem: ArrayList<DesignModel> = ArrayList()

    private var id: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_buy_now_cart, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            id = requireArguments().getString("id")
        }
        apiBuyCartGemItem()
        apiHitSimilarProductVendor()

        onClick()

    }


    private fun apiBuyCartGemItem() {
        val appPrefs = AppPrefs(requireContext())

        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.getFilterData(token,requireArguments().getString("id"))
        //enqueue method
        retrofitData.enqueue(object : Callback<AddCartNew> {

            override fun onResponse(call: Call<AddCartNew>, response: Response<AddCartNew>) {
                if (response.code() == 200) {
                    mergeListJeweller.clear()
                    imageListGemsVendor.clear()
                    videoListGemsVendor.clear()
                    viewCaratListVendor = response.body()!!
                    imageListGemsVendor.addAll(response.body()!!.gemImages)
                    videoListGemsVendor.addAll(response.body()!!.gemVideos)
                    mergeListJeweller.addAll(imageListGemsVendor)
                    mergeListJeweller.addAll(videoListGemsVendor)

                    if (viewCaratListVendor!!.addedToCart){
                        binding.tvAddToCart.text="REMOVE TO CART"

                    }else{
                        binding.tvAddToCart.text="ADD TO CART"

                    }


                    callViewPagerBuyNow(requireArguments().getString("id")!!)
                    setDataCart()
                    //  binding.progressBarAddCart.visibility=View.GONE
                    Toast.makeText(requireActivity(), "Successful", Toast.LENGTH_SHORT).show()
                } else {
                    //  binding.progressBarAddCart.visibility=View.GONE
                    Toast.makeText(requireActivity(), "Error 400", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AddCartNew>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error! " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun mergerList() {
        imageListGemsVendor.forEachIndexed { index, data ->

        }
        videoListGemsVendor.forEachIndexed { index, data ->

        }
    }

    private fun setDataCart() {
        binding.tvCaratDataEmeraldBuyNow.text = viewCaratListVendor!!.name
        binding.tvCaratNameBuyNow.text = viewCaratListVendor!!.name
        binding.tvDataEmeraldBuy.text = viewCaratListVendor!!.name
        binding.tvCaratPriceDataEmerald.text=viewCaratListVendor?.price
        binding.tvDataSkuCaratValueBuyNow.text = viewCaratListVendor!!.sku
        binding.tvDataCaratOriginValueBuyNow.text = viewCaratListVendor!!.origin.name
        binding.tvDataAddCaratDollarBuyNow.text = viewCaratListVendor!!.price
        binding.tvCaratNameBuyNowPrice.text = viewCaratListVendor!!.price

        binding.tvStoneCut.text=viewCaratListVendor?.stoneCut?.name
        binding.tvStoneQuality.text=viewCaratListVendor?.stoneQuality?.name
        binding.tvShape.text=viewCaratListVendor?.shape?.name
        binding.tvColor.text=viewCaratListVendor?.categoryColor?.name
        binding.tvExactDimensions.text=viewCaratListVendor?.dimensionHeight?.toString()
        binding.tvWeight.text=viewCaratListVendor?.weight?.toString()
    }

    private fun callViewPagerBuyNow(id:String) {
        viewPagerBuyNowAdapter = ViewPagerBuyNowAdapter(requireActivity(), (mergeListJeweller ?: arrayListOf()) as ArrayList<GemVideosItem>,id)
        binding.viewPagerCaratBuyNow.adapter = viewPagerBuyNowAdapter
        binding.tabLayCaratBuyNow.setupWithViewPager(binding.viewPagerCaratBuyNow, true)
    }

    private fun apiHitSimilarProductVendor() {
        val appPrefs = AppPrefs(requireContext())

        val token = "Token" + " " + appPrefs.getToken("token")
        hashMapDataVendor()
        val retrofitData = RetrofitInstance().retrofitBuilder.similarProduct(token,hashMap)
        //enqueue method
        retrofitData.enqueue(object : Callback<SimilarProductsResponse> {

            override fun onResponse(
                call: Call<SimilarProductsResponse>,
                response: Response<SimilarProductsResponse>
            ) {
                if (response.code() == 200) {
                    dataSimilarListVendor.clear()
                    dataSimilarListVendor.addAll(response.body()!!.data)
                    recyclerSimilarItem()
                    for(i in dataSimilarListVendor!!.indices){
                        binding.iconAddToCartVendor.text= dataSimilarListVendor!![i].cartCount.toString()
                    }
                    //   binding.progressBarAddCart.visibility=View.GONE
                    Toast.makeText(requireActivity(), "Successful", Toast.LENGTH_SHORT).show()
                } else {
                    //   binding.progressBarAddCart.visibility=View.GONE
                    Toast.makeText(requireActivity(), "Error 400", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SimilarProductsResponse>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error! " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun recyclerSimilarItem() {
        similarProductVendorAdapter = SimilarProductVendorAdapter(this, requireContext(), dataSimilarListVendor)
        binding.rvSpVendor.adapter = similarProductVendorAdapter
    }

    fun addToWishListApiHit(id: Int) {
        val appPrefs = AppPrefs(requireContext())
        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.addToWishlist(token,id)
        //enqueue method
        retrofitData.enqueue(object : Callback<AddtoWishListModel> {

            override fun onResponse(
                call: Call<AddtoWishListModel>,
                response: Response<AddtoWishListModel>
            ) {
                if (response.code() == 201) {

                    Toast.makeText(requireActivity(), "Add To WishList", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(), "Already Add To WishList", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<AddtoWishListModel>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error! " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun apiCallAddToCart(id: Int) {
        buyNowCartModel.gem= id
        val appPrefs = AppPrefs(requireContext())
        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.addToCart(token,buyNowCartModel)
        //enqueue method
        retrofitData.enqueue(object : Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.code() == 201) {
                    binding!!.iconAddToCartVendor.text = response.body()?.get("user_cart_count").toString()
                    Toast.makeText(requireActivity(), "Add To Shopping Cart", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(), "Already Add", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error! " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun removeFromCartListApi(gem_id: Int) {
        val appPrefs = AppPrefs(requireContext())
        hashMapDataVendor()
        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.removeFromCart(token,gem_id)
        //enqueue method

        retrofitData.enqueue(object : Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.code() == 200) {
                  //  listScAdd.removeAt(position)
                    binding!!.iconAddToCartVendor.text = response.body()?.get("user_cart_count").toString()
                  //  shoppingCartVendorAdapter?.notifyDataSetChanged()
                    Toast.makeText(requireActivity(), "Remove Item", Toast.LENGTH_SHORT).show()
                } else {
                    //  binding.progressBarBringOnline.visibility=View.GONE
                    Toast.makeText(requireActivity(), response.body().toString(), Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error! " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun removeFromListApiWishList(id: Int) {
        val appPrefs = AppPrefs(requireContext())

        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.removeWishlist(token,id)
        //enqueue method
        retrofitData.enqueue(object : Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.code() == 200) {
                    similarProductVendorAdapter!!.notifyDataSetChanged()
                    Toast.makeText(requireActivity(), "Remove Item", Toast.LENGTH_SHORT).show()
                } else {
                    //  binding.progressBarBringOnline.visibility=View.GONE
                    Toast.makeText(requireActivity(), response.body().toString(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error! " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onClick() {
        binding.constraintBuyNow.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, SecurityCheckOut())
                ?.addToBackStack("")?.commit()
        }
        binding.backPressedBuyNow.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.tvViewCertificate.setOnClickListener {
            val productDetailsBottomSheet = ProductDetailsBottomSheet()
            productDetailsBottomSheet.isCancelable = false
            productDetailsBottomSheet.show(
                requireActivity().supportFragmentManager,
                "ModalBottomSheet"
            )
        }
        binding.cartBringOnlineVendor.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, ShoppingCartVendor())?.addToBackStack("")?.commit()
        }

/*        binding.spinnerAddJewellery.setOnTouchListener { p0, p1 ->
            binding.constraintVisi.visibility = View.VISIBLE
            false
        }*/

 /*       binding.spinnerAddJewellery.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                }
            }*/
        binding.tvAddToCart.setOnClickListener {
                binding.iconAddToCartVendor.text= viewCaratListVendor!!.cartCount.toString()
            if (viewCaratListVendor!!.addedToCart){
                removeFromCartListApi((viewCaratListVendor?.id!!.toInt()))
                viewCaratListVendor!!.addedToCart = false
                binding.tvAddToCart.text="ADD TO CART"

            }else{
                apiCallAddToCart(viewCaratListVendor!!.id)
                viewCaratListVendor!!.addedToCart = true

                binding.tvAddToCart.text="REMOVE TO CART"
            }


        }
    }

/*    private fun addListItemRing() {
        listRingItem.clear()
        listRingItem.add(DesignModel(BitmapFactory.decodeResource(this.resources, R.drawable.ring_pic), "REMYG01", "$59"))
        listRingItem.add(DesignModel(BitmapFactory.decodeResource(this.resources, R.drawable.ring_pic), "REMYG01", "$59"))
        listRingItem.add(DesignModel(BitmapFactory.decodeResource(this.resources, R.drawable.ring_pic), "REMYG01", "$59"))
        listRingItem.add(DesignModel(BitmapFactory.decodeResource(this.resources, R.drawable.ring_pic), "REMYG01", "$59"))
    }*/

/*    private fun recyclerRingItem() {
        designAdapter = DesignAdapter(listRingItem)
        binding.rvRingBuy.adapter = designAdapter
    }

    private fun addJewellerySpinner() {
        val list = arrayOf("Add A Jewellery", "Jewellery", "Jewellery")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAddJewellery?.adapter = adapter
    }

    private fun ringSpinner() {
        val list = arrayOf("Ring", "Ring", "Ring")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRing?.adapter = adapter
    }

    private fun yellowGoldSpinner() {
        val list = arrayOf(" Yellow", "Yellow", "Yellow")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        yellowGoldSpinner?.adapter = adapter
    }

    private fun ringSizeSystemSpinner() {
        val list = arrayOf("Size System", "Size System", "Size System")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRingSizeSystem?.adapter = adapter
    }

    private fun ringSizeSpinner() {
        val list = arrayOf("Please Select", "10", "20")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRingSize?.adapter = adapter
    }*/

    fun hashMapDataVendor() {
        hashMap["category_id"] = category_id_vendor.toString()
        hashMap["gem_id"] = id!!
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding!!.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

}
