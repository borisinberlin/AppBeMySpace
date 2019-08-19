package boris.com.appbemyspace.data.model

import com.google.gson.annotations.SerializedName

data class currentUser(

    @SerializedName("code") var code: Int,
    @SerializedName("message") var message: String,
    @SerializedName("payload") var user: UserInfo
)

data class UserInfo(

    @SerializedName("id") var user_id: Int,
    @SerializedName("username") var username: String,
    @SerializedName("password") var password: String,
    @SerializedName("email") var email: String,
    @SerializedName("firstName") var firstName: String,
    @SerializedName("lastName") var lasteName: String,
    @SerializedName("token") var token: String
)

data class NewUser(

    var firstName: String,
    var lasteName: String,
    var username: String,
    var email: String,
    var password: String

)

data class LoggingUser(

    var username: String,
    var password: String
)