package com.example.beginvegan.config

import android.app.Application
import com.example.beginvegan.BuildConfig
import com.kakao.vectormap.KakaoMapSdk
import timber.log.Timber

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        KakaoMapSdk.init(this, BuildConfig.KAKAO_API_KEY);
    }

    //    override fun onCreate() {
//        super.onCreate()
//        sSharedPreferences =
//            applicationContext.getSharedPreferences("BeginVegan", MODE_PRIVATE)
//        initRetrofitInstance()
//    }
//    companion object {
//        lateinit var sRetrofit: Retrofit
//        lateinit var sSharedPreferences: SharedPreferences
//
//    }
//    private fun initRetrofitInstance() {
//        val client: OkHttpClient = OkHttpClient.Builder()
//            .readTimeout(5000, TimeUnit.MILLISECONDS)
//            .connectTimeout(5000, TimeUnit.MILLISECONDS)
//            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//            .addNetworkInterceptor(AccessTokenInterceptor()) // JWT 자동 헤더 전송
//            .build()
//        sRetrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

}