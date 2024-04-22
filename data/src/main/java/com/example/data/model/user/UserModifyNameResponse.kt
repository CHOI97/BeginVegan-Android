package com.example.data.model.user

import com.example.beginvegan.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class UserModifyNameResponse(
    @SerializedName("information") val information: com.example.data.model.user.ModifyName
): BaseResponse()

data class ModifyName(
    @SerializedName("message") val message:String
)