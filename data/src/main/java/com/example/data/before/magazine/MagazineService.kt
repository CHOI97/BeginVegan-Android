package com.example.data.before.magazine//package com.example.data.model.magazine
//
//import com.example.beginvegan.config.ApplicationClass
//import com.example.beginvegan.config.ErrorResponse
//import com.example.presentation.src.data.api.MagazineRetrofitInterface
//import com.example.beginvegan.util.Constants
//import com.example.beginvegan.util.Constants.ACCESS_TOKEN
//import com.google.gson.Gson
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.create
//
//class MagazineService(val magazineInterface: com.example.data.model.magazine.MagazineInterface) {
//    private val magazineRetrofitInterface: com.example.presentation.src.data.api.MagazineRetrofitInterface = ApplicationClass.sRetrofit.create(
//        com.example.presentation.src.data.api.MagazineRetrofitInterface::class.java)
//
//    // 2가지 매거진 목록 조회
//    fun tryGetMagazineTwoList(){
//        magazineRetrofitInterface.getMagazineTwoList(ApplicationClass.xAccessToken).enqueue(object: Callback<com.example.data.model.magazine.MagazineTwoResponse>{
//            override fun onResponse(
//                call: Call<com.example.data.model.magazine.MagazineTwoResponse>,
//                response: Response<com.example.data.model.magazine.MagazineTwoResponse>
//            ) {
//                if(response.code() == 200){
//                    magazineInterface.onGetMagazineTwoListSuccess(response.body() as com.example.data.model.magazine.MagazineTwoResponse)
//                }else{
//                    try{
//                        val gson = Gson()
//                        val errorResponse =
//                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
//                        magazineInterface.onGetMagazineTwoListFailure(errorResponse.message)
//                    }catch(e:Exception){
//                        magazineInterface.onGetMagazineTwoListFailure(e.message?:"통신 오류")
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.data.model.magazine.MagazineTwoResponse>, t: Throwable) {
//                magazineInterface.onGetMagazineTwoListFailure(t.message?:"통신 오류")
//            }
//
//        })
//    }
//
//    // 매거진 상세 정보 조회
//    fun tryPostMagazineDetail(magazineId: Int){
//        magazineRetrofitInterface.postMagazineDetail(ApplicationClass.xAccessToken,magazineId).enqueue(object: Callback<com.example.data.model.magazine.MagazineDetailResponse>{
//            override fun onResponse(
//                call: Call<com.example.data.model.magazine.MagazineDetailResponse>,
//                response: Response<com.example.data.model.magazine.MagazineDetailResponse>
//            ) {
//                if(response.code() == 200){
//                    magazineInterface.onPostMagazineDetailSuccess(response.body() as com.example.data.model.magazine.MagazineDetailResponse)
//                }else{
//                    try{
//                        val gson = Gson()
//                        val errorResponse =
//                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
//                        magazineInterface.onPostMagazineDetailFailure(errorResponse.message)
//                    }catch(e:Exception){
//                        magazineInterface.onPostMagazineDetailFailure(e.message?:"통신 오류")
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.data.model.magazine.MagazineDetailResponse>, t: Throwable) {
//                magazineInterface.onPostMagazineDetailFailure(t.message?:"통신 오류")
//            }
//
//        })
//    }
//
//}