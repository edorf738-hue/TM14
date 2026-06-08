package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BarangAdapter(
    private val daftarBarang: List<Barang>,
    private val onItemClick: (Barang) -> Unit
) : RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {

    inner class BarangViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNama: TextView = itemView.findViewById(R.id.tvNamaBarang)
        val tvKategori: TextView = itemView.findViewById(R.id.tvKategori)
        val tvHarga: TextView = itemView.findViewById(R.id.tvHarga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_barang, parent, false)
        return BarangViewHolder(view)
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val barang = daftarBarang[position]
        holder.tvNama.text = barang.namaBarang
        holder.tvKategori.text = barang.kategori
        holder.tvHarga.text = "Rp ${barang.harga}"

        holder.itemView.setOnClickListener {
            onItemClick(barang)
        }
    }

    override fun getItemCount() = daftarBarang.size
}
