package boris.com.appbemyspace.data.model

import com.google.gson.annotations.SerializedName


data class UserProfil(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("numberObjects") val numberObjects: Int,
    @SerializedName("payload") val payload: UserProfilInfo,
    @SerializedName("success") val success: Boolean
)

data class UserProfilInfo(
    @SerializedName("avatar") val avatar: Any,
    @SerializedName("deviceToken") val deviceToken: Any,
    @SerializedName("email") val email: Any,
    @SerializedName("host") val host: Boolean,
    @SerializedName("stripeHostId") val stripeHostId: Any,
    @SerializedName("stripeId") val stripeId: String,
    @SerializedName("userStatus") val userStatus: UserProfilStatus,
    @SerializedName("username") val username: String
)

data class UserProfilStatus(
    @SerializedName("code") val code: String,
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: Int
)