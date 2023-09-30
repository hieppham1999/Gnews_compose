package com.hieppt.enrich.gnew.data.api

import com.hieppt.enrich.gnew.values.GNEWS_API_KEY
import com.hieppt.enrich.gnew.values.GNEWS_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

interface ArticleApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category: String,
        @Query("lang") lang: String = "en",
        @Query("apikey") apikey: String = GNEWS_API_KEY
    ) : Resource<ArticleList>

    companion object {

        fun create(): ArticleApiService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(GNEWS_BASE_URL)
                .client(client)
                .addCallAdapterFactory(MyCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ArticleApiService::class.java)
        }
    }
}
abstract class CallDelegate<TIn, TOut>(
    protected val proxy: Call<TIn>
) : Call<TOut> {
    override fun execute(): Response<TOut> = throw NotImplementedError()
    final override fun enqueue(callback: Callback<TOut>) = enqueueImpl(callback)
    final override fun clone(): Call<TOut> = cloneImpl()

    override fun cancel() = proxy.cancel()
    override fun request(): Request = proxy.request()
    override fun isExecuted() = proxy.isExecuted
    override fun isCanceled() = proxy.isCanceled

    abstract fun enqueueImpl(callback: Callback<TOut>)
    abstract fun cloneImpl(): Call<TOut>
}

class ResultCall<T>(proxy: Call<T>) : CallDelegate<T, Resource<T>>(proxy) {
    override fun enqueueImpl(callback: Callback<Resource<T>>) = proxy.enqueue(object: Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                callback.onResponse(
                    this@ResultCall,
                    Response.success(Resource.success(response.body()))
                )
            } else {

                callback.onResponse(
                    this@ResultCall,
                    Response.success(
                        Resource.error(msg = "Status code: ${response.code()}", data = null)
                    )
                )
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            val errorMessage = when (t) {
                is IOException -> "No internet connection"
                is HttpException -> "Something went wrong!"
                else -> t.localizedMessage
            }
            callback.onResponse(
                this@ResultCall,
                Response.success(Resource.error(msg = errorMessage, data = null)))

        }
    })

    override fun cloneImpl() = ResultCall(proxy.clone())
    override fun timeout(): Timeout {
        return proxy.timeout()
    }
}

class ResultAdapter(
    private val type: Type
): CallAdapter<Type, Call<Resource<Type>>> {
    override fun responseType() = type
    override fun adapt(call: Call<Type>): Call<Resource<Type>> = ResultCall(call)
}

class MyCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ) = when (getRawType(returnType)) {
        Call::class.java -> {
            val callType = getParameterUpperBound(0, returnType as ParameterizedType)
            when (getRawType(callType)) {
                Resource::class.java -> {
                    val resultType = getParameterUpperBound(0, callType as ParameterizedType)
                    ResultAdapter(resultType)
                }
                else -> null
            }
        }
        else -> null
    }
}
