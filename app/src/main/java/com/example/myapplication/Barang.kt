package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Barang(
    val id: Int,
    @SerializedName("nama_barang") val namaBarang: String,
    val kategori: String,
    val stok: Int,
    val harga: Int,
    val deskripsi: String
)
