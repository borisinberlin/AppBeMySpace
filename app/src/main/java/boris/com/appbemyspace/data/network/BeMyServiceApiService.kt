package boris.com.appbemyspace.data.network

import boris.com.appbemyspace.data.model.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface BeMyServiceApiService {

    @Headers("Content-Type:application/json",
        "appKey:BEMYSPACE")
    @POST("login")
    fun login (@Body user: LoggingUser): Deferred<currentUser>

    @GET("/user/profile/{username}")
    fun retriveUser(@Header("Accept") type: String,@Header("token") token : String,
                    @Path("username")username :String):Deferred<currentUser>

    //"token:eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNTYzNTQ2NzA4LCJuYW1lIjoidGVzdCIsInNjb3BlIjoiVVNFUiJ9.2esEivNFn6A4XRANpVzVcvKvSz1yvtbef-zlH68rQwA")
    @POST("spaces/search")
    fun getSpaceList(@Header("Content-Type") type:String, @Header("token") token:String,
                     @Body searchData: SearchData):Deferred<SpaceData>

    @GET("/user/{username}/{host}")
    fun upgradeUserState(
        @Header("Content-Type") type: String, @Header("token") token: String,
        @Path("host") host: Boolean, @Path("username") username: String
    ): Deferred<EmptyResultDataModel>

    @POST("/account")
    fun upgradeUser(
        @Header("Content-Type") type: String, @Header("token") token: String,
        @Body upgradeInfo: UserUpgradeBody
    ): Deferred<EmptyResultDataModel>

    object LoginApiService {

        val loginRetrofitService by lazy {
            loginRetrofit.create(BeMyServiceApiService::class.java)
        }


    }

    object ApiService{

        val retrofitService by lazy {
            retrofit.create(BeMyServiceApiService::class.java)
        }
    }



    companion object {

        private val loginRetrofit=Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://173.249.32.170:8195/")
            .build()


        private val retrofit=Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://173.249.32.170:8193/bemyspace/")
            .build()

       /* operator fun invoke(): BeMyServiceApiService {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    //  .addHeader("Content-Type","application/json")
                    // .addHeader("token","eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNTYzNjk3NjUxLCJuYW1lIjoidGVzdCIsInNjb3BlIjoiVVNFUiJ9.A00Mhmw0bYZDLDYRtIVM7lxz6tv7yF3kYjQ3NlChi04")
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                // .baseUrl("http://173.249.32.170:8193/bemyspace/")
                .baseUrl("http://173.249.32.170:8195/")
                //.baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BeMyServiceApiService::class.java)
        }*/

    }


}