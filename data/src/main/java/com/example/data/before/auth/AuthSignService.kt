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
//class AuthSignService(val authSignInterface: com.example.data.model.auth.AuthSignInterface) {
//    private val authRetrofitInterface: com.example.presentation.src.data.api.AuthRetrofitInterface = ApplicationClass.sRetrofit.create(
//        com.example.presentation.src.data.api.AuthRetrofitInterface::class.java)
//
//    fun tryPostAuthSignIn(providerId: String,email: String){
//        authRetrofitInterface.postAuthSignIn(
//            com.example.data.model.auth.AuthLogin(
//                providerId,
//                email
//            )
//        ).enqueue(object: Callback<com.example.data.model.auth.AuthSignResponse>{
//            override fun onResponse(call: Call<com.example.data.model.auth.AuthSignResponse>, response: Response<com.example.data.model.auth.AuthSignResponse>) {
//                if(response.code() == 200){
//                    authSignInterface.onPostAuthSignInSuccess(response.body() as com.example.data.model.auth.AuthSignResponse)
//                }else{
//                    try{
//                        val gson = Gson()
//                        val errorResponse =
//                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
//                        authSignInterface.onPostAuthSignInFailed(errorResponse.message)
//                    }catch(e:Exception){
//                        authSignInterface.onPostAuthSignInFailed(e.message?:"통신 오류")
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.data.model.auth.AuthSignResponse>, t: Throwable) {
//                authSignInterface.onPostAuthSignInFailed(t.message?:"통신 오류")
//            }
//
//        })
//    }
//    fun tryPostAuthSignUp(auth: com.example.data.model.auth.KakaoAuth){
//        authRetrofitInterface.postAuthSignUp(auth).enqueue(object: Callback<com.example.data.model.auth.AuthSignResponse> {
//            override fun onResponse(call: Call<com.example.data.model.auth.AuthSignResponse>, response: Response<com.example.data.model.auth.AuthSignResponse>) {
//                if(response.code() == 200){
//                    authSignInterface.onPostAuthSignUpSuccess(response.body() as com.example.data.model.auth.AuthSignResponse)
//                }else{
//                    try{
//                        val gson = Gson()
//                        val errorResponse =
//                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
//                        authSignInterface.onPostAuthSignUpFailed(errorResponse.message)
//                    }catch(e:Exception){
//                        authSignInterface.onPostAuthSignUpFailed(e.message?:"통신 오류")
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.data.model.auth.AuthSignResponse>, t: Throwable) {
//                authSignInterface.onPostAuthSignUpFailed(t.message?:"통신 오류")
//            }
//
//        })
//    }
//
//}