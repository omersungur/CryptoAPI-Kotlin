package com.omersungur.cryptoapi_kotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.omersungur.cryptoapi_kotlin.adapter.CryptoAdapter
import com.omersungur.cryptoapi_kotlin.databinding.ActivityMainBinding
import com.omersungur.cryptoapi_kotlin.model.CryptoModel
import com.omersungur.cryptoapi_kotlin.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity() : AppCompatActivity() {


    private lateinit var cryptoList : ArrayList<CryptoModel>
    private lateinit var binding: ActivityMainBinding
    private lateinit var cryptoAdapter: CryptoAdapter
    private val BASE_URL = "https://raw.githubusercontent.com/"
    var cryptoModel : ArrayList<CryptoModel>? = null
    private var compositeDisposable : CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        compositeDisposable = CompositeDisposable()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        loadData()
    }

    fun loadData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)

        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))

        /*

        Call kullandığımızda bu yapıyı kullanabiliriz ama composite disposable ve rxJava kullanımı için Observable kullandık.
        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        //enqueue > asenkron işlem yapmamıza olanak tanır.
        call.enqueue(object : Callback<List<CryptoModel>> {
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>,
            ) {
                if(response.isSuccessful) {
                    response.body()?.let {
                        cryptoModel = ArrayList(it)
                        cryptoModel?.let {
                            cryptoAdapter = CryptoAdapter(it)
                            binding.recyclerView.adapter = cryptoAdapter
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })*/
    }

    private fun handleResponse(cryptoList : List<CryptoModel>) {
        cryptoModel = ArrayList(cryptoList)
        cryptoModel?.let {
            cryptoAdapter = CryptoAdapter(it)
            binding.recyclerView.adapter = cryptoAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}