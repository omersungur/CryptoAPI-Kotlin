package com.omersungur.cryptoapi_kotlin.model

import com.google.gson.annotations.SerializedName

data class CryptoModel (
    //@SerializedName("currency") // API'daki veri setinde bulunan verinin ismi.
    val currency : String,

    //@SerializedName("price")
    val price : String)