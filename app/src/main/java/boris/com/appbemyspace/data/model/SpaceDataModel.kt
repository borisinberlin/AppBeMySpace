package boris.com.appbemyspace.data.model

import com.google.gson.annotations.SerializedName


data class SpaceData (
@SerializedName("code") val code: Int,
@SerializedName("message") val message: String,
@SerializedName("payload") val payload: List<SpaceInfo>,
@SerializedName("numberObjects") val numberObjects: Int,
@SerializedName("success") val success: Boolean
)

data class SearchData(
    var address: String
   // var limit: Int,
    //var page: Int
)


data class SpaceInfo(

    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("address") val address: String,
    @SerializedName("zipCode") val zipCode: String,
    @SerializedName("city") val city: String,
    @SerializedName("country") val country: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("email") val email: String,
    @SerializedName("floorType") val floorType: FloorType,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
    @SerializedName("technicalFeatures") val technicalFeatures: List<TechnicalFeature>,
    @SerializedName("feeHour") val feeHour: Int,
    @SerializedName("currency") val currency: Currency,
    @SerializedName("images") val images: List<String>,
    @SerializedName("minHour") val minHour: Int,
    @SerializedName("week") val week: Week,
    @SerializedName("maxGuests") val maxGuests: Int,
    @SerializedName("size") val size: Int,
    @SerializedName("website") val website: String,
    @SerializedName("refunds") val refunds: Any

)


data class Currency(
    @SerializedName("code") val code: String,
    @SerializedName("country") val country: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("symbol") val symbol: String
)


data class FloorType(
    @SerializedName("id") val id: Int,
    @SerializedName("code") val code: String,
    @SerializedName("description") val description: String
)

data class Owner(
    @SerializedName("username") val username: String,
    @SerializedName("avatar") val avatar: Any,
    @SerializedName("host") val host: Boolean,
    @SerializedName("stripeId") val stripeId: String,
    @SerializedName("stripeHostId") val stripeHostId: String,
    @SerializedName("email") val email: Any,
    @SerializedName("deviceToken") val deviceToken: Any
    // @SerializedName("userStatus") val userStatus: UserStatus
)

data class TechnicalFeature(
    @SerializedName("id") val id: Int,
    @SerializedName("code") val code: String,
    @SerializedName("description") val description: String
)

data class UserStatus(
    @SerializedName("id") val id: Int,
    @SerializedName("code") val code: String,
    @SerializedName("description") val description: String
)


data class Week(
    @SerializedName("id") val id: Int,
    @SerializedName("jsonContent") val jsonContent: String,
    @SerializedName("current") val current: Boolean,
    @SerializedName("space") val space: Any,
    @SerializedName("name") val name: String,
    @SerializedName("discounts") val discounts: Any
)