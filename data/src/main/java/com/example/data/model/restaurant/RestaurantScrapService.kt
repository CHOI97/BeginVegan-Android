//package com.example.data.model.restaurant
//
//import com.example.beginvegan.config.ApplicationClass
//import com.example.beginvegan.config.ErrorResponse
//import com.example.presentation.src.data.api.RestaurantRetrofitInterface
//import com.google.gson.Gson
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class RestaurantScrapService(val restaurantScrapInterface: com.example.data.model.restaurant.RestaurantScrapInterface) {
//    private val restaurantRetrofitInterface: com.example.presentation.src.data.api.RestaurantRetrofitInterface =
//        ApplicationClass.sRetrofit.create(com.example.presentation.src.data.api.RestaurantRetrofitInterface::class.java)
//
//    fun tryPostScrapRestaurant(restaurantId: Int) {
//        restaurantRetrofitInterface.postScrapRestaurant(ApplicationClass.xAccessToken, restaurantId)
//            .enqueue(object :
//                Callback<com.example.data.model.restaurant.RestaurantScrapResponse> {
//                override fun onResponse(
//                    call: Call<com.example.data.model.restaurant.RestaurantScrapResponse>,
//                    response: Response<com.example.data.model.restaurant.RestaurantScrapResponse>
//                ) {
//                    if (response.code() == 200) {
//                        restaurantScrapInterface.onPostScrapRestaurantSuccess(response.body() as com.example.data.model.restaurant.RestaurantScrapResponse)
//                    } else {
//                        try {
//                            val gson = Gson()
//                            val errorResponse =
//                                gson.fromJson(
//                                    response.errorBody()?.string(),
//                                    ErrorResponse::class.java
//                                )
//                            restaurantScrapInterface.onPostScrapRestaurantFailure(errorResponse.message)
//                        } catch (e: Exception) {
//                            restaurantScrapInterface.onPostScrapRestaurantFailure(
//                                e.message ?: "통신 오류"
//                            )
//                        }
//
//                    }
//                }
//
//                override fun onFailure(call: Call<com.example.data.model.restaurant.RestaurantScrapResponse>, t: Throwable) {
//                    restaurantScrapInterface.onPostScrapRestaurantFailure(t.message ?: "통신 오류")
//                }
//
//            })
//    }
//}