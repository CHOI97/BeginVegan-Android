//package com.example.data.model.user
//
//import com.example.beginvegan.config.ApplicationClass
//import com.example.beginvegan.config.ErrorResponse
//import com.example.presentation.src.data.api.UserRetrofitInterface
//import com.example.beginvegan.util.VeganTypes
//import com.google.gson.Gson
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class UserVeganService(val userInterface: com.example.data.model.user.UserInterface){
//    private val userRetrofitInterface: com.example.presentation.src.data.api.UserRetrofitInterface = ApplicationClass.sRetrofit.create(
//        com.example.presentation.src.data.api.UserRetrofitInterface::class.java)
//
//    fun tryPostUserVeganType(veganType: String){
//        userRetrofitInterface.postUserVeganType(ApplicationClass.xAccessToken,
//            com.example.data.model.user.VeganType(veganType)
//        ).enqueue(object: Callback<com.example.data.model.user.UserVeganResponse>{
//            override fun onResponse(
//                call: Call<com.example.data.model.user.UserVeganResponse>,
//                response: Response<com.example.data.model.user.UserVeganResponse>
//            ) {
//                if(response.code() == 200){
//                    userInterface.onPostUserVeganTypeSuccess(response.body() as com.example.data.model.user.UserVeganResponse)
//                }else{
//                    try{
//                        val gson = Gson()
//                        val errorResponse =
//                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
//                        userInterface.onPostUserVeganTypeFailure(errorResponse.message)
//                    }catch(e:Exception){
//                        userInterface.onPostUserVeganTypeFailure(e.message?:"통신 오류")
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.data.model.user.UserVeganResponse>, t: Throwable) {
//                userInterface.onPostUserVeganTypeFailure(t.message?:"통신 오류")
//            }
//
//        })
//    }
//}