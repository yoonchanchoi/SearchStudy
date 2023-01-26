package com.example.searchstudy.network.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class JwtTokenRes(
    @SerializedName("head")
    @Expose
    val baseHeadRes: BaseHeadRes,
    @SerializedName("body")
    @Expose
    val jwtTokenBodyRes: JwtTokenBodyRes
)

data class JwtTokenBodyRes(

    @SerializedName("isValidateJwtToken")
    @Expose
    val isValidateJwtToken : Boolean
)



//
//data class JwtTokenBodyRes(
//    @SerializedName("isValidateJwtToken")
//    @Expose
//    val isValidateJwtToken : Boolean
//) : BaseHeadRes()








