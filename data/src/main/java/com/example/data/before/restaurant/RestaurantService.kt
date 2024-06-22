//package com.example.data.model.restaurant
//
//import com.example.beginvegan.config.ApplicationClass
//import com.example.presentation.src.data.api.RestaurantRetrofitInterface
//import com.example.beginvegan.config.ErrorResponse
//import com.example.beginvegan.util.Constants
//import com.google.gson.Gson
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class RestaurantService(val restaurantInterface: com.example.data.model.restaurant.RestaurantInterface){
//    private val restaurantRetrofitInterface: com.example.presentation.src.data.api.RestaurantRetrofitInterface = ApplicationClass.sRetrofit.create(
//        com.example.presentation.src.data.api.RestaurantRetrofitInterface::class.java)
//
//    // 식당/카페 상세 정보(메뉴까지) 조회
//    fun tryGetRestaurantDetail(restaurantId: Int){
//        restaurantRetrofitInterface.getRestaurantsDetail(ApplicationClass.xAccessToken,restaurantId).enqueue(object: Callback<com.example.data.model.restaurant.RestaurantDetailResponse>{
//            override fun onResponse(
//                call: Call<com.example.data.model.restaurant.RestaurantDetailResponse>,
//                response: Response<com.example.data.model.restaurant.RestaurantDetailResponse>
//            ) {
//                if(response.code()==200){
//                    restaurantInterface.onGetRestaurantDetailSuccess(response.body() as com.example.data.model.restaurant.RestaurantDetailResponse)
//                }else{
//                    try{
//                        val gson = Gson()
//                        val errorResponse =
//                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
//                        restaurantInterface.onGetRestaurantDetailFailure(errorResponse.message)
//                    }catch(e:Exception){
//                        restaurantInterface.onGetRestaurantDetailFailure(e.message?:"통신 오류")
//                    }
//
//                }
//            }
//            override fun onFailure(call: Call<com.example.data.model.restaurant.RestaurantDetailResponse>, t: Throwable) {
//                restaurantInterface.onGetRestaurantDetailFailure(t.message?:"통신 오류")
//            }
//
//        })
//    }
//
//    // 식당/카페 리뷰 조회
//    fun tryGetRestaurantReview(restaurantId: Int,page: Int){
//        restaurantRetrofitInterface.getRestaurantReview(ApplicationClass.xAccessToken,restaurantId,page).enqueue(object: Callback<com.example.data.model.restaurant.RestaurantReviewResponse>{
//            override fun onResponse(
//                call: Call<com.example.data.model.restaurant.RestaurantReviewResponse>,
//                response: Response<com.example.data.model.restaurant.RestaurantReviewResponse>
//            ) {
//                if(response.code()==200){
//                    if(page==0){
//                        restaurantInterface.onGetRestaurantReviewSuccess(response.body() as com.example.data.model.restaurant.RestaurantReviewResponse)
//                    }else{
//                        restaurantInterface.onGetRestaurantReviewAddSuccess(response.body() as com.example.data.model.restaurant.RestaurantReviewResponse)
//                    }
//
//                }
//                else{
//                    try{
//                        val gson = Gson()
//                        val errorResponse =
//                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
//                        restaurantInterface.onGetRestaurantReviewFailure(errorResponse.message)
//                    }catch(e:Exception){
//                        restaurantInterface.onGetRestaurantReviewFailure(e.message?:"통신 오류")
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.data.model.restaurant.RestaurantReviewResponse>, t: Throwable) {
//                restaurantInterface.onGetRestaurantReviewFailure(t.message?:"통신 오류")
//            }
//
//        })
//    }
//
//
//}