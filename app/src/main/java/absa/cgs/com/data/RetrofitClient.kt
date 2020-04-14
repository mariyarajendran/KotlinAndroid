package absa.cgs.com.data

import absa.cgs.com.kotlinplayground.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient {

    private const val BASE_URL = "https://cabelsoft.000webhostapp.com/"

    val interceptor = HttpLoggingInterceptor()


//    private val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor { chain ->
//                val httpLoggingInterceptor = HttpLoggingInterceptor()
//                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//                val original = chain.request()
//                val requestBuilder = original.newBuilder()
//                        .method(original.method(), original.body())
//                        .addHeader("Content-Type", "application/json")
//                        .addHeader("Accept", "application/json")
//                val request = requestBuilder.build()
//                chain.proceed(request)
//
//            }.build()

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    private val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(40, TimeUnit.SECONDS)
            .addNetworkInterceptor(httpLoggingInterceptor())
            .build()


    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        retrofit.create(Api::class.java)
    }
}