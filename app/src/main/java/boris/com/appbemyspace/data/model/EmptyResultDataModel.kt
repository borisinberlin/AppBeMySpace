package boris.com.appbemyspace.data.model

import com.google.gson.annotations.SerializedName


data class EmptyResultDataModel(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("numberObjects") val numberObjects: Int,
    @SerializedName("payload") val payload: String,
    @SerializedName("success") val success: Boolean
)