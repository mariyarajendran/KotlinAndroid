package absa.cgs.com.data

import absa.cgs.com.kotlinplayground.BuildConfig
import absa.cgs.com.utils.SingletonUtils
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


object RetrofitClient {

    private const val BASE_URL = "https://cabelsoft.000webhostapp.com/"

    //private const val BASE_URL = "http://192.168.43.147/"


    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                        .method(original.method(), original.body())
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json")
                        .addHeader("Authorization", SingletonUtils.instance.authToken)
                val request = requestBuilder.build()
                chain.proceed(request)

            }.addInterceptor(httpLoggingInterceptor())
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(40, TimeUnit.SECONDS)
            .build()

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        retrofit.create(Api::class.java)
    }
}