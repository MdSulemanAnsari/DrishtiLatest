package com.application.drishtigems.ui.fragment

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.application.drishtigems.R
import com.application.drishtigems.databinding.FragmentEditProfileBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import java.text.SimpleDateFormat
import java.util.*
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import android.graphics.BitmapFactory
import com.application.drishtigems.Network.StaffNetwork.ApiModel.StateDataModel.ApiDataModel
import com.application.drishtigems.Network.StaffNetwork.ApiModel.StateDataModel.Cities
import com.application.drishtigems.Network.StaffNetwork.ApiModel.StateDataModel.States
import com.application.drishtigems.Network.RetrofitInstance
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList
import java.io.*
import android.util.Log
import android.widget.*
import androidx.drawerlayout.widget.DrawerLayout
import com.application.drishtigems.Network.StaffNetwork.ApiModel.EditProfileModel.EditProfileDataClass
import com.application.drishtigems.Network.StaffNetwork.ApiModel.LoginUserModel.UserModel
import com.application.drishtigems.VendorUser.fragment.MyProfileVendor.MyProfileVendor
import com.application.drishtigems.prefs.AppPrefs
import com.application.drishtigems.ui.activity.MainActivity
import com.bumptech.glide.Glide
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class EditProfile : Fragment() {
    private val stateListDetailsTemp: ArrayList<String> = ArrayList()
    private val cityListDetailsTemp: ArrayList<String> = ArrayList()
    lateinit var binding: FragmentEditProfileBinding
    var textDate: TextView? = null
    var imageBitmap: Bitmap? = null
    private  var data: UserModel? =null
    val listState: ArrayList<States> = ArrayList()
    var listCities: ArrayList<Cities> = ArrayList()

    var     : HashMap<String, RequestBody?> = HashMap()
    var pictureGalley: MultipartBody.Part? = null
    var imgPaths: File? = null

    var cal = Calendar.getInstance()
    var cityId: Int? = null
    var stateId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_profile, container, false)
        return binding.root
    }

    private fun getFileName(): String {
        return "${Random().nextInt(5)}testingfile_12.png"
    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    @RequiresApi(Build.VERSION_CODES.O)

    @SuppressLint("WrongConstant", "WrongThread")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appPrefs = AppPrefs(requireContext())
        data = appPrefs.getModelKey("model")
        //appPrefs.setUpdate("update",updatePref)


        binding.progressBar.visibility = View.VISIBLE
        // Storing the email using share preference

        apiCallState()
       // apiCallCity(listState[position].id)
        val appPref = AppPrefs(requireContext())
        val s1 = appPref.getModelKey("model")
        tvEditProfileEmail.text = s1!!.email
        onClick()
        setDatePicker()
       getDataBundle()
    }

    private fun getDataBundle() {
        if (arguments!=null){
            Glide.with(requireActivity()).load(requireArguments().getString("image")).into(profileEditPic)
            etFirstName.setText(requireArguments().getString("firstNameProfile"))
            etLastName.setText(requireArguments().getString("lastNameProfile"))
            etPhoneProfile.setText(requireArguments().getString("phoneNumberProfile"))
            datePicker.text = requireArguments().getString("dobProfile")
            etAddress.setText(requireArguments().getString("addressProfile"))
            etPinCode.setText(requireArguments().getString("pinCode"))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onClick() {
        binding.buttonUpdateProfile.setOnClickListener {
            checkCondition()
        //    (activity as MainActivity).refreshDrawerData()
        }
        binding.profileEditPic.setOnClickListener {
            imagePicker()
        }
        binding.backPressedEditProfile.setOnClickListener {
           requireActivity().onBackPressed()
        }
    }


    private fun updateDateInView() {
        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textDate!!.text = sdf.format(cal.getTime())
    }

    private fun apiCallState() {
        val retrofitData = RetrofitInstance().retrofitBuilder.getStates()
        //enqueue method
        retrofitData.enqueue(object : Callback<ApiDataModel> {
            override fun onResponse(call: Call<ApiDataModel>, response: Response<ApiDataModel>) {
                if (response.code() == 200) {
                    listState.addAll(response.body()!!.statesList)
                 //   listCities.addAll(response.body()!!.citiesList)
                    if (listState != null && listState.size > 0) {
                        for (i in listState.indices) {
                            stateListDetailsTemp.add(listState[i].name)
                        }
                        stateSpinner()
                        setStateValues()
                    }
                    binding.progressBar.visibility =View.GONE
                    Log.e("response", response.body().toString())
                } else {
                    binding.progressBar.visibility =View.GONE
                    Toast.makeText(requireContext(), "Not Responding", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiDataModel>, t: Throwable) {
                binding.progressBar.visibility =View.GONE
                Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun apiCallCity(id: Int) {
        val retrofitData = RetrofitInstance().retrofitBuilder.getCities(id)
        //enqueue method
        retrofitData.enqueue(object : Callback<ArrayList<Cities>> {
            override fun onResponse(call: Call<ArrayList<Cities>>, response: Response<ArrayList<Cities>>) {
                if (response.code() == 200) {
                    listCities.clear()
                    listCities.addAll(response.body()?: arrayListOf())
                    //   listCities.addAll(response.body()!!.citiesList)
                    cityListDetailsTemp.clear()
                    if (listCities != null && listCities.size > 0) {
                        for (i in listCities.indices) {
                            cityListDetailsTemp.add(listCities[i].name)
                        }
                        citiesSpinner()
                        setCityValue()
                    }
                    binding.progressBar.visibility =View.GONE
                    Log.e("response", response.body().toString())
                } else {
                    binding.progressBar.visibility =View.GONE
                    Toast.makeText(requireContext(), "Not Responding", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<Cities>>, t: Throwable) {
                binding.progressBar.visibility =View.GONE
                Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun stateSpinner() {
        val spinnerArrayAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, stateListDetailsTemp)
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // The drop down view
        spinnerState.adapter = spinnerArrayAdapter
        spinnerState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                stateId = listState[position].id
                apiCallCity(listState[position].id)
              /*      if (listCities != null && listCities.size > 0) {
                    for (i in listCities.indices) {
                        if (listState[position].id == listCities[i].state) {
                            cityListDetails.add(listCities[i])
                            cityListDetailsTemp.add(listCities[i].name)
                        }
                    }
                        citiesSpinner()
                       // setCityValue()
                }*/
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    private fun setStateValues(){
        try {
            val newSetState = requireArguments().getString("addressState_id")
            val filterState = listState.filter { it.id.toString() == newSetState }
            if (!filterState.isNullOrEmpty()){
                spinnerState.setSelection(listState.indexOf(filterState[0]))
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    private fun setCityValue(){
       try {
            val newCity = requireArguments().getString("addressCity_id")
            val filterCity = listCities.filter { it.id.toString() == newCity }
            if (!filterCity.isNullOrEmpty()){
                spinnerCities.setSelection(listCities.indexOf(filterCity[0]))
            }}
        catch (e:Exception){
            e.printStackTrace()
        }

    }
    private fun citiesSpinner() {
        val spinnerArrayAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, cityListDetailsTemp)
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // The drop down view
        spinnerCities.adapter = spinnerArrayAdapter
        spinnerCities.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                cityId = listCities[position].id


            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    @SuppressLint("WrongConstant")
    private fun apiCallEditProfile() {
        setHashMapEditProfile()
        val appPrefs = AppPrefs(requireContext())
        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.editProfile(token, hashMap, pictureGalley)
        //enqueue method
        retrofitData.enqueue(object : Callback<EditProfileDataClass> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<EditProfileDataClass>,
                response: Response<EditProfileDataClass>
            ) {
                if (response.code() == 200) {

                    data = appPrefs.getModelKey("model")
                    if (data!!.groups[0].name == "Sales Executive"){
                        fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, MyProfile())?.addToBackStack("") ?.commit()
                    }
                    else if(data!!.groups[0].name == "Jeweller"){
                        fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, MyProfileVendor())?.addToBackStack("") ?.commit()
                    }

                } else {
                    Toast.makeText(requireActivity(), "Error 400", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<EditProfileDataClass>, t: Throwable) {

                Toast.makeText(requireActivity(), "Error!", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun setHashMapEditProfile() {
        hashMap["first_name"] = etFirstName.text.toString().toRequestBody("text/form-data".toMediaTypeOrNull())
        hashMap["last_name"] = etLastName.text.toString().toRequestBody("text/form-data".toMediaTypeOrNull())
        hashMap["email"] = tvEditProfileEmail.text.toString().toRequestBody("text/form-data".toMediaTypeOrNull())
        hashMap["phone_number"] = etPhoneProfile.text.toString().toRequestBody("text/form-data".toMediaTypeOrNull())
        hashMap["address"] = etAddress.text.toString().toRequestBody("text/form-data".toMediaTypeOrNull())
        hashMap["dob"] = datePicker.text.toString().toRequestBody("text/form-data".toMediaTypeOrNull())
        hashMap["address_state"] = stateId.toString().toRequestBody("text/form-data".toMediaTypeOrNull())
        hashMap["address_city"] = cityId.toString().toRequestBody("text/form-data".toMediaTypeOrNull())
        hashMap["address_pin_code"] = etPinCode.text.toString().toRequestBody("text/form-data".toMediaTypeOrNull())
        val requestFile = imgPaths?.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        if (requestFile != null) {
            pictureGalley = MultipartBody.Part.createFormData("image", imgPaths?.name, requestFile)
        }
    }

    private fun convertBitmapToFile() {
        val file = File(requireActivity().cacheDir, getFileName())
        file.createNewFile()
        val bos = ByteArrayOutputStream()
        imageBitmap?.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos)
        val bitmapdata = bos.toByteArray()
        val fos = FileOutputStream(file)
        fos.write(bitmapdata)
        imgPaths = file
        fos.flush()
        fos.close()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkCondition() {
        val firstName = etFirstName.text.toString()
        val lastName = etLastName.text.toString()

        if (firstName.length==0) {
            etFirstName.requestFocus()
         etFirstName.error="First Name Can't Empty"
            return
        }
        if (lastName.length==0) {
            etLastName.requestFocus()
          etLastName.error="Second Name Can't Empty"
            return
        }
   /*     if (imageBitmap!= null) {
            Toast.makeText(requireContext(), "Please Select Image", Toast.LENGTH_SHORT).show()
            return
        }*/

        apiCallEditProfile()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun decodeBase64(input: String?): Bitmap? {
        val decodedByte: ByteArray = Base64.getDecoder().decode(input)
        return BitmapFactory
            .decodeByteArray(decodedByte, 0, decodedByte.size)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun encodeTobase64(image: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b: ByteArray = baos.toByteArray()
        val imageEncoded: String = Base64.getEncoder().encodeToString(b)
        return imageEncoded
    }

    //date picker
    private fun setDatePicker() {
        //set date of birth set using custom calender in dialog box
        textDate = this.datePicker
        textDate!!.text = "Select Date of Birth"
        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }
        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        textDate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                // set DatePickerDialog to point to today's date when it loads up
                DatePickerDialog(
                    requireContext(),
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })
    }

    //image picker
    private fun imagePicker() {
        ImagePicker.with(this)
            .crop()                    //Crop image(Optional), Check Customization for more option
            .compress(1024)            //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                1080,
                1080
            )    //Final image resolution will be less than 1080 x 1080(Optional)
            .start()
    }

    //image picker activity result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val uri: Uri = data?.data!!
            imageBitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
            //convert bitmap to file
            convertBitmapToFile()
            profileEditPic.setImageURI(uri)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(requireActivity(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireActivity(), "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}