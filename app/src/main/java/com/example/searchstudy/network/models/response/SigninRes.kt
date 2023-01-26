package com.example.searchstudy.network.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SigninRes(
    @SerializedName("head")
    @Expose
    val baseHeadRes: BaseHeadRes,
    @SerializedName("body")
    @Expose
    val signinBodyRes: SigninBodyRes
)

data class SigninBodyRes(
    @SerializedName("id")
    @Expose
    val id: Long,

    @SerializedName("userId")
    @Expose
    val userId: String,

    @SerializedName("userNm")
    @Expose
    val userNm: String,

    @SerializedName("email")
    @Expose
    val email: String,

    @SerializedName("roles")
    @Expose
    val roles: Array<String>,

    @SerializedName("token")
    @Expose
    val token: String,

    @SerializedName("refreshToken")
    @Expose
    val refreshToken: String,

    @SerializedName("isValidateJwtToken")
    @Expose
    val isValidateJwtToken: Boolean
)
























//data class SigninBodyRes(
//    @SerializedName("id")
//    @Expose
//    val id: Long,
//
//    @SerializedName("userId")
//    @Expose
//    val userId: String,
//
//    @SerializedName("userNm")
//    @Expose
//    val userNm: String,
//
//    @SerializedName("email")
//    @Expose
//    val email: String,
//
//    @SerializedName("roles")
//    @Expose
//    val roles: Array<String>,
//
//    @SerializedName("token")
//    @Expose
//    val token: String,
//
//    @SerializedName("refreshToken")
//    @Expose
//    val refreshToken: String,
//
//    @SerializedName("isValidateJwtToken")
//    @Expose
//    val isValidateJwtToken: Boolean
//) : BaseHeadRes()


