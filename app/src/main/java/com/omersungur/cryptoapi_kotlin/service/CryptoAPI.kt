package com.omersungur.cryptoapi_kotlin.service

import com.omersungur.cryptoapi_kotlin.model.CryptoModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {

    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData() : Observable<List<CryptoModel>>
    //fun getData() : Call<List<CryptoModel>>
}