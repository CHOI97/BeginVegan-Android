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
//class UserModifyNameService(val userModifyNameInterface: com.example.data.model.user.UserModifyNameInterface) {
//    private val userRetrofitInterface: com.example.presentation.src.data.api.UserRetrofitInterface =
//        ApplicationClass.sRetrofit.create(com.example.presentation.src.data.api.UserRetrofitInterface::class.java)
//
//    fun tryPostUserModifyName(nickname: String) {
//        userRetrofitInterface.postUserModifyName(ApplicationClass.xAccessToken,
//            com.example.data.model.user.NickName(nickname)
//        ).enqueue(object :
//            Callback<com.example.data.model.user.UserModifyNameResponse> {
//            override fun onResponse(
//                call: Call<com.example.data.model.user.UserModifyNameResponse>,
//                response: Response<com.example.data.model.user.UserModifyNameResponse>
//            ) {
//                if(response.code() == 200){
//                    userModifyNameInterface.onPostUserModifyNameSuccess(response.body() as com.example.data.model.user.UserModifyNameResponse)
//                }else{
//                    try{
//                        val gson = Gson()
//                        val errorResponse =
//                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
//                        userModifyNameInterface.onPostUserModifyNameFailure(errorResponse.message)
//                    }catch(e:Exception){
//                        userModifyNameInterface.onPostUserModifyNameFailure(e.message?:"통신 오류")
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.data.model.user.UserModifyNameResponse>, t: Throwable) {
//                userModifyNameInterface.onPostUserModifyNameFailure(t.message?:"통신 오류")
//            }
//
//        })
//    }
//}