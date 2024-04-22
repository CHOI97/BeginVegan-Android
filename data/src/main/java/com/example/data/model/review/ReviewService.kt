//package com.example.data.model.review
//
//import com.example.beginvegan.config.ApplicationClass
//import com.example.beginvegan.config.ErrorResponse
//import com.example.presentation.src.data.api.ReviewRetrofitInterface
//import com.example.beginvegan.util.Constants
//import com.google.gson.Gson
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class ReviewService(val reviewInterface: com.example.data.model.review.ReviewInterface) {
//    private val reviewRetrofitInterface: com.example.presentation.src.data.api.ReviewRetrofitInterface =
//        ApplicationClass.sRetrofit.create(com.example.presentation.src.data.api.ReviewRetrofitInterface::class.java)
//    fun tryPostWriteReview(restaurantId: Int, content: String) {
//        reviewRetrofitInterface.postWriteReview(ApplicationClass.xAccessToken,
//            com.example.data.model.review.ReviewRequest(restaurantId, content)
//        )
//            .enqueue(object : Callback<com.example.data.model.review.WriteReviewResponse> {
//                override fun onResponse(
//                    call: Call<com.example.data.model.review.WriteReviewResponse>,
//                    response: Response<com.example.data.model.review.WriteReviewResponse>
//                ) {
//                    if (response.code() == 200) {
//                        reviewInterface.onPostWriteReviewSuccess(response.body() as com.example.data.model.review.WriteReviewResponse)
//                    } else {
//                        try {
//                            val gson = Gson()
//                            val errorResponse =
//                                gson.fromJson(
//                                    response.errorBody()?.string(),
//                                    ErrorResponse::class.java
//                                )
//                            reviewInterface.onPostWriteReviewFailure(errorResponse.message)
//                        } catch (e: Exception) {
//                            reviewInterface.onPostWriteReviewFailure(e.message ?: "통신 오류")
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<com.example.data.model.review.WriteReviewResponse>, t: Throwable) {
//                    reviewInterface.onPostWriteReviewFailure(t.message ?: "통신 오류")
//                }
//
//            })
//    }
//
//    fun tryGetReviewList(pageNo: Int) {
//        reviewRetrofitInterface.getReviewList(ApplicationClass.xAccessToken,pageNo).enqueue(object : Callback<com.example.data.model.review.ReviewListResponse> {
//            override fun onResponse(
//                call: Call<com.example.data.model.review.ReviewListResponse>,
//                response: Response<com.example.data.model.review.ReviewListResponse>
//            ) {
//                if (response.code() == 200) {
//                    if(pageNo == 0){
//                        reviewInterface.onGetReviewListSuccess(response.body() as com.example.data.model.review.ReviewListResponse)
//                    }else{
//                        reviewInterface.onGetReviewListAddSuccess(response.body() as com.example.data.model.review.ReviewListResponse)
//                    }
//                } else {
//                    try {
//                        val gson = Gson()
//                        val errorResponse =
//                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
//                        reviewInterface.onGetReviewListFailure(errorResponse.message)
//                    } catch (e: Exception) {
//                        reviewInterface.onGetReviewListFailure(e.message ?: "통신 오류")
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.data.model.review.ReviewListResponse>, t: Throwable) {
//                reviewInterface.onGetReviewListFailure(t.message ?: "통신 오류")
//            }
//
//        })
//    }
//}