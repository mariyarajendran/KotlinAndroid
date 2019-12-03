package absa.cgs.com.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    private const val BASE_URL = "http://106.51.0.40:8025/api/"

    val interceptor = HttpLoggingInterceptor()

    private val okHttpClient = OkHttpClient.Builder()
            //.addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC))
            .addInterceptor { chain ->
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                        .method(original.method(), original.body())
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json")
                val request = requestBuilder.build()
                chain.proceed(request)
            }.build()


    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        retrofit.create(Api::class.java)
    }
}