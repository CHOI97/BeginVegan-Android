package com.example.data.before.auth//package com.example.data.model.auth
//
//import com.example.beginvegan.config.ApplicationClass
//import com.example.beginvegan.config.ErrorResponse
//import com.example.presentation.src.data.api.AuthRetrofitInterface
//import com.google.gson.Gson
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class AuthTokenRefreshService(val authTokenRefreshInterface: com.example.data.model.auth.AuthTokenRefreshInterface) {
//    private val authRetrofitInterface: com.example.presentation.src.data.api.AuthRetrofitInterface = ApplicationClass.sRetrofit.create(
//        com.example.presentation.src.data.api.AuthRetrofitInterface::class.java)
//
//    fun tryPostTokenRefreshSuccess(refreshToken: String){
//        authRetrofitInterface.postTokenRefresh(refreshToken).enqueue(object: Callback<com.example.data.model.auth.AuthTokenResponse>{
//            override fun onResponse(call: Call<com.example.data.model.auth.AuthTokenResponse>, response: Response<com.example.data.model.auth.AuthTokenResponse>) {
//                if(response.code() == 200){
//                    authTokenRefreshInterface.onPostTokenRefreshSuccess(response.body() as com.example.data.model.auth.AuthTokenResponse)
//                }else{
//                    try{
//                        val gson = Gson()
//                        val errorResponse =
//                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
//                        authTokenRefreshInterface.onPostTokenRefreshFailure(errorResponse.message)
//                    }catch(e:Exception){
//                        authTokenRefreshInterface.onPostTokenRefreshFailure(e.message?:"통신 오류")
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.data.model.auth.AuthTokenResponse>, t: Throwable) {
//                authTokenRefreshInterface.onPostTokenRefreshFailure(t.message?:"통신 오류")
//            }
//
//        })
//    }
//}