//package com.example.data.model.user
//
//import com.example.beginvegan.config.ApplicationClass
//import com.example.beginvegan.config.ErrorResponse
//import com.example.presentation.src.data.api.UserRetrofitInterface
//import com.google.gson.Gson
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class UserCheckService(val userCheckInterface: com.example.data.model.user.UserCheckInterface) {
//    private val userRetrofitInterface: com.example.presentation.src.data.api.UserRetrofitInterface = ApplicationClass.sRetrofit.create(
//        com.example.presentation.src.data.api.UserRetrofitInterface::class.java)
//
//    fun tryGetUser(){
//        userRetrofitInterface.getUser(ApplicationClass.xAccessToken).enqueue(object: Callback<com.example.data.model.user.UserResponse> {
//            override fun onResponse(call: Call<com.example.data.model.user.UserResponse>, response: Response<com.example.data.model.user.UserResponse>) {
//                if(response.code() == 200){
//                    userCheckInterface.onGetUserSuccess(response.body() as com.example.data.model.user.UserResponse)
//                }else{
//                    try{
//                        val gson = Gson()
//                        val errorResponse =
//                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
//                        userCheckInterface.onGetUserFailure(errorResponse.message)
//                    }catch(e:Exception){
//                        userCheckInterface.onGetUserFailure(e.message?:"통신 오류")
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.data.model.user.UserResponse>, t: Throwable) {
//                userCheckInterface.onGetUserFailure(t.message?:"통신 오류")
//            }
//
//        })
//    }
//}