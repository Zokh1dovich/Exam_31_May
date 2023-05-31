package BekhrUzBEK.exam

import BekhrUzBEK.exam.databinding.ActivityMainBinding
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var myRvAdapter: MyRvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Retrofit2()

    }
    private fun Retrofit2(){

        val apiService = ApiClient.getRetrofit().create(ApiService::class.java)
        apiService.getMarvels()
            .enqueue(object : Callback<List<APIDataItem>> {
                override fun onResponse(
                    call: Call<List<APIDataItem>>,
                    response: Response<List<APIDataItem>>
                ) {
                    if (response.isSuccessful && response.body()!=null){
                        Log.d(ContentValues.TAG, "onResponse: ${response.body()}")
                        binding.apply {
                            val dataList = response.body()
                            val adapter = MyRvAdapter(dataList!!)
                            rv.adapter = adapter
                        }

                    }
                }

                override fun onFailure(call: Call<List<APIDataItem>>, t: Throwable) {

                    Toast.makeText(this@MainActivity, " Internetni tekshiring!! ", Toast.LENGTH_SHORT).show()

                }



            })
//        binding.btnSearch.setOnQueryTextFocusChangeListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                var searchQuery= newText ?: ""
//                return true
//            }
//        })
    }
}
