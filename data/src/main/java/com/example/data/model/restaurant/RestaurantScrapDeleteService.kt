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
//class RestaurantScrapDeleteService(val restaurantScrapDeleteInterface: com.example.data.model.restaurant.RestaurantScrapDeleteInterface) {
//    private val restaurantRetrofitInterface: com.example.presentation.src.data.api.RestaurantRetrofitInterface = ApplicationClass.sRetrofit.create(
//        com.example.presentation.src.data.api.RestaurantRetrofitInterface::class.java)
//
//    fun tryDeleteScrapRestaurant(restaurantId: Int){
//        restaurantRetrofitInterface.deleteScrapRestaurant(ApplicationClass.xAccessToken,restaurantId).enqueue(object:
//            Callback<com.example.data.model.restaurant.RestaurantScrapDeleteResponse> {
//            override fun onResponse(
//                call: Call<com.example.data.model.restaurant.RestaurantScrapDeleteResponse>,
//                response: Response<com.example.data.model.restaurant.RestaurantScrapDeleteResponse>
//            ) {
//                if(response.code()==200){
//                    restaurantScrapDeleteInterface.onDeleteScrapRestaurantSuccess(response.body() as com.example.data.model.restaurant.RestaurantScrapDeleteResponse)
//                }else{
//                    try{
//                        val gson = Gson()
//                        val errorResponse =
//                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
//                        restaurantScrapDeleteInterface.onDeleteScrapRestaurantFailure(errorResponse.message)
//                    }catch(e:Exception){
//                        restaurantScrapDeleteInterface.onDeleteScrapRestaurantFailure(e.message?:"통신 오류")
//                    }
//
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.data.model.restaurant.RestaurantScrapDeleteResponse>, t: Throwable) {
//                restaurantScrapDeleteInterface.onDeleteScrapRestaurantFailure(t.message?:"통신 오류")
//            }
//
//        })
//    }
//}