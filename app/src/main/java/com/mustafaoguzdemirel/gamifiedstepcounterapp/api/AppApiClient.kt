package com.mustafaoguzdemirel.gamifiedstepcounterapp.api

import android.text.TextUtils
import android.util.Log
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.Dataholder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object AppApiClient {
    fun getClientWithToken(): Retrofit {
        val headerLogging = HttpLoggingInterceptor()
        val bodyLogging = HttpLoggingInterceptor()
        headerLogging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        bodyLogging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(bodyLogging)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(
                Interceptor { chain: Interceptor.Chain ->
                    Log.e("token", Dataholder.instance.currentUserModel?.token!!)
                    var request: Request.Builder = chain.request().newBuilder()
                    if (!TextUtils.isEmpty(Dataholder.instance.currentUserModel?.token)) request =
                        request.addHeader(
                            "Authorization",
                            "Bearer " + Dataholder.instance.currentUserModel?.token
                        )
                    request = request.method(chain.request().method, chain.request().body)
                    chain.proceed(request.build())
                }).build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://gidysoft.com:2000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val clientWithOutToken: Retrofit
        get() {
            val headerLogging = HttpLoggingInterceptor()
            val bodyLogging = HttpLoggingInterceptor()
            headerLogging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
            bodyLogging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(bodyLogging)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(
                    Interceptor { chain: Interceptor.Chain ->
                        var request: Request.Builder = chain.request().newBuilder()
                        request = request.method(chain.request().method, chain.request().body)
                        chain.proceed(request.build())
                    }).build()
            return Retrofit.Builder()
                .client(client)
                .baseUrl("https://gidysoft.com:2000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
}
