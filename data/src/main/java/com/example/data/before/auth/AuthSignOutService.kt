package com.example.data.before.auth//package com.example.data.model.auth
//
//import com.example.beginvegan.config.ApplicationClass
//import com.example.beginvegan.config.ErrorResponse
//import com.example.presentation.src.data.api.AuthRetrofitInterface
//import com.example.beginvegan.src.data.model.magazine.MagazineTwoResponse
//import com.google.gson.Gson
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.create
//
//class AuthSignOutService(val authSignOutInterface: com.example.data.model.auth.AuthSignOutInterface) {
//    private val authRetrofitInterface: com.example.presentation.src.data.api.AuthRetrofitInterface =
//        ApplicationClass.sRetrofit.create(com.example.presentation.src.data.api.AuthRetrofitInterface::class.java)
//
//    fun tryPostAuthSignOut() {
//        authRetrofitInterface.postAuthSignOut(ApplicationClass.xAccessToken).enqueue(object :
//            Callback<com.example.data.model.auth.AuthSignOutResponse> {
//            override fun onResponse(
//                call: Call<com.example.data.model.auth.AuthSignOutResponse>,
//                response: Response<com.example.data.model.auth.AuthSignOutResponse>
//            ) {
//                if(response.code() == 200){
//                    authSignOutInterface.onPostAuthSignOutSuccess(response.body() as com.example.data.model.auth.AuthSignOutResponse)
//                }else{
//                    try{
//                        val gson = Gson()
//                        val errorResponse =
//                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
//                        authSignOutInterface.onPostAuthSignOutFailure(errorResponse.message)
//                    }catch(e:Exception){
//                        authSignOutInterface.onPostAuthSignOutFailure(e.message?:"통신 오류")
//                    }
//                }
//
//            }
//
//            override fun onFailure(call: Call<com.example.data.model.auth.AuthSignOutResponse>, t: Throwable) {
//                authSignOutInterface.onPostAuthSignOutFailure(t.message?:"통신 오류")
//            }
//
//        })
//
//    }
//}