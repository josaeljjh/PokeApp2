package com.jjh.pokeapp2.repository

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.jjh.pokeapp2.BuildConfig
import com.jjh.pokeapp2.utils.NetworkConnectionInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class DataRepository {

     fun provideOkhttpClient(): OkHttpClient {
        try {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            builder.addInterceptor(loggingInterceptor)
            builder.addInterceptor(NetworkConnectionInterceptor())
            builder.connectTimeout(300,TimeUnit.SECONDS)
            builder.readTimeout(80,TimeUnit.SECONDS)
            builder.writeTimeout(90,TimeUnit.SECONDS)
            builder.retryOnConnectionFailure(true)
            builder.addNetworkInterceptor(Interceptor {
                val request: Request = it.request().newBuilder().addHeader("Connection", "close").build()
                return@Interceptor it.proceed(request)
            })
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(OkHttpProfilerInterceptor())
            }
            return builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
    inline fun <reified T>getRetrofit(url: String): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(provideOkhttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(T::class.java)
    }


    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

}