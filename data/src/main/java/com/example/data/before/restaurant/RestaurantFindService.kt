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
//class RestaurantFindService(val restaurantFindInterface: com.example.data.model.restaurant.RestaurantFindInterface){
//    private val restaurantRetrofitInterface: com.example.presentation.src.data.api.RestaurantRetrofitInterface = ApplicationClass.sRetrofit.create(
//        com.example.presentation.src.data.api.RestaurantRetrofitInterface::class.java)
//
//    fun tryPostFindRestaurant(coordinate: com.example.data.model.restaurant.Coordinate){
//        restaurantRetrofitInterface.postFindRestaurant(ApplicationClass.xAccessToken,coordinate).enqueue(object :
//            Callback<com.example.data.model.restaurant.RestaurantFindResponse>{
//            override fun onResponse(
//                call: Call<com.example.data.model.restaurant.RestaurantFindResponse>,
//                response: Response<com.example.data.model.restaurant.RestaurantFindResponse>
//            ) {
//                if(response.code()==200){
//                    restaurantFindInterface.onPostFindRestaurantSuccess(response.body() as com.example.data.model.restaurant.RestaurantFindResponse)
//                }else{
//                    try{
//                        val gson = Gson()
//                        val errorResponse =
//                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
//                        restaurantFindInterface.onPostFindRestaurantFailure(errorResponse.message)
//                    }catch(e:Exception){
//                        restaurantFindInterface.onPostFindRestaurantFailure(e.message?:"통신 오류")
//                    }
//
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.data.model.restaurant.RestaurantFindResponse>, t: Throwable) {
//                restaurantFindInterface.onPostFindRestaurantFailure(t.message?:"통신 오류")
//            }
//
//        })
//    }
//}