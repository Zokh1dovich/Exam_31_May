package BekhrUzBEK.exam

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    fun getMarvels():Call<List<APIDataItem>>
}