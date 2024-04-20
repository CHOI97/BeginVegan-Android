//package com.example.data.model.recipe
//
//import com.example.beginvegan.config.ApplicationClass
//import com.example.beginvegan.config.ErrorResponse
//import com.example.presentation.src.data.api.RecipeRetrofitInterface
//import com.google.gson.Gson
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//class RecipeService(val recipeInterface: com.example.data.model.recipe.RecipeInterface) {
//    private val recipeRetrofitInterface: com.example.presentation.src.data.api.RecipeRetrofitInterface =
//        ApplicationClass.sRetrofit.create(com.example.presentation.src.data.api.RecipeRetrofitInterface::class.java)
//    // 전체 레시피 목록 조회
//    fun tryGetRecipeList() {
//        recipeRetrofitInterface.getRecipeList(ApplicationClass.xAccessToken)
//            .enqueue(object : Callback<com.example.data.model.recipe.RecipeListResponse> {
//                override fun onResponse(
//                    call: Call<com.example.data.model.recipe.RecipeListResponse>,
//                    response: Response<com.example.data.model.recipe.RecipeListResponse>
//                ) {
//                    if (response.code() == 200) {
//                        recipeInterface.onGetRecipeListSuccess(response.body() as com.example.data.model.recipe.RecipeListResponse)
//                    } else {
//                        try {
//                            val gson = Gson()
//                            val errorResponse =
//                                gson.fromJson(
//                                    response.errorBody()?.string(),
//                                    ErrorResponse::class.java
//                                )
//                            recipeInterface.onGetRecipeListFailure(errorResponse.message)
//                        } catch (e: Exception) {
//                            recipeInterface.onGetRecipeListFailure(e.message ?: "통신 오류")
//                        }
//                    }
//
//                }
//
//                override fun onFailure(call: Call<com.example.data.model.recipe.RecipeListResponse>, t: Throwable) {
//                    recipeInterface.onGetRecipeListFailure(t.message ?: "통신 오류")
//                }
//
//            })
//    }
//
//    // 3가지 음식 목록 조회
//    fun tryGetThreeRecipeList() {
//        recipeRetrofitInterface.getThreeRecipeList(ApplicationClass.xAccessToken)
//            .enqueue(object : Callback<com.example.data.model.recipe.RecipeThreeResponse> {
//                override fun onResponse(
//                    call: Call<com.example.data.model.recipe.RecipeThreeResponse>,
//                    response: Response<com.example.data.model.recipe.RecipeThreeResponse>
//                ) {
//                    if (response.code() == 200) {
//                        recipeInterface.onGetThreeRecipeListSuccess(response.body() as com.example.data.model.recipe.RecipeThreeResponse)
//                    } else {
//                        try {
//                            val gson = Gson()
//                            val errorResponse =
//                                gson.fromJson(
//                                    response.errorBody()?.string(),
//                                    ErrorResponse::class.java
//                                )
//                            recipeInterface.onGetThreeRecipeListFailure(errorResponse.message)
//                        } catch (e: Exception) {
//                            recipeInterface.onGetThreeRecipeListFailure(e.message ?: "통신 오류")
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<com.example.data.model.recipe.RecipeThreeResponse>, t: Throwable) {
//                    recipeInterface.onGetThreeRecipeListFailure(t.message ?: "통신 오류")
//                }
//
//            })
//
//    }
//
//    // 레시피 상세 정보 조회
//    fun tryPostRecipeDetail(recipeId: Int) {
//        recipeRetrofitInterface.postRecipeDetail(ApplicationClass.xAccessToken,recipeId)
//            .enqueue(object : Callback<com.example.data.model.recipe.RecipeDetailResponse> {
//                override fun onResponse(
//                    call: Call<com.example.data.model.recipe.RecipeDetailResponse>,
//                    response: Response<com.example.data.model.recipe.RecipeDetailResponse>
//                ) {
//                    if (response.code() == 200) {
//                        recipeInterface.onPostRecipeDetailSuccess(response.body() as com.example.data.model.recipe.RecipeDetailResponse)
//                    } else {
//                        try {
//                            val gson = Gson()
//                            val errorResponse =
//                                gson.fromJson(
//                                    response.errorBody()?.string(),
//                                    ErrorResponse::class.java
//                                )
//                            recipeInterface.onPostRecipeDetailFailure(errorResponse.message)
//                        } catch (e: Exception) {
//                            recipeInterface.onPostRecipeDetailFailure(e.message ?: "통신 오류")
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<com.example.data.model.recipe.RecipeDetailResponse>, t: Throwable) {
//                    recipeInterface.onPostRecipeDetailFailure(t.message ?: "통신 오류")
//                }
//
//            })
//
//    }
//}