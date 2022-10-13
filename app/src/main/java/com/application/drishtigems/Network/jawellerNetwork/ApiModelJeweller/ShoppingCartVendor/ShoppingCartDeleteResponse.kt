

data class ShoppingCartDeleteResponse(
    val userCartCount: Int,
    val data: List<DataItem>
)

data class DataItem(
    val addedToWishlist: Boolean,
    val createdTime: String,
    val expectedDeliveryTime: String,
    val certificationType: Any,
    val id: Int,
    val user: User,
    val gem: Any,
    val jewelleryType: Any,
    val addedToCart: Boolean
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
    val addressState: Int,
    val lastName: String,
    val groups: List<Int>,
    val addressCity: Int,
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

