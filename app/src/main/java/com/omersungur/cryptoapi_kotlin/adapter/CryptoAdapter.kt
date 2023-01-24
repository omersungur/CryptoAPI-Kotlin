package com.omersungur.cryptoapi_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.omersungur.cryptoapi_kotlin.databinding.RecyclerRowBinding
import com.omersungur.cryptoapi_kotlin.model.CryptoModel

class CryptoAdapter(val cryptoList : List<CryptoModel>) : RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {

    class CryptoHolder(val binding : RecyclerRowBinding) : ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CryptoHolder(binding)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.binding.recyclerViewCurrencyText.text = cryptoList[position].currency
        holder.binding.recyclerViewPriceText.text = cryptoList[position].price
        println(cryptoList[position].currency)
        println(cryptoList[position].price)

    }
}