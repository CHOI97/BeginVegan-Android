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
//class UserScrapService(val userScrapInterface: com.example.data.model.user.UserScrapInterface) {
//    private val userRetrofitInterface: com.example.presentation.src.data.api.UserRetrofitInterface = ApplicationClass.sRetrofit.create(
//        com.example.presentation.src.data.api.UserRetrofitInterface::class.java)
//
//    fun tryGetUserBookmarks(pageNo:Int){
//        userRetrofitInterface.getUserBookmarks(ApplicationClass.xAccessToken,pageNo).enqueue(object:
//            Callback<com.example.data.model.user.UserScrapResponse> {
//            override fun onResponse(
//                call: Call<com.example.data.model.user.UserScrapResponse>,
//                response: Response<com.example.data.model.user.UserScrapResponse>
//            ) {
//                if(response.code() == 200){
//                    if(pageNo == 0){
//                        userScrapInterface.onGetUserBookmarksSuccess(response.body() as com.example.data.model.user.UserScrapResponse)
//                    }else{
//                        userScrapInterface.onGetUserBookmarksAddSuccess(response.body() as com.example.data.model.user.UserScrapResponse)
//                    }
//                }else{
//                    try{
//                        val gson = Gson()
//                        val errorResponse =
//                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
//                        userScrapInterface.onGetUserBookmarksFailure(errorResponse.message)
//                    }catch(e:Exception){
//                        userScrapInterface.onGetUserBookmarksFailure(e.message?:"통신 오류")
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.data.model.user.UserScrapResponse>, t: Throwable) {
//                userScrapInterface.onGetUserBookmarksFailure(t.message?:"통신 오류")
//            }
//
//        })
//    }
//}