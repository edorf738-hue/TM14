package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentFirstBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBarang.layoutManager = LinearLayoutManager(requireContext())

        RetrofitClient.instance.getBarang().enqueue(object : Callback<ApiResponse<List<Barang>>> {
            override fun onResponse(
                call: Call<ApiResponse<List<Barang>>>,
                response: Response<ApiResponse<List<Barang>>>
            ) {
                if (response.isSuccessful && response.body()?.success == true) {
                    val barangList = response.body()?.data ?: emptyList()
                    val adapter = BarangAdapter(barangList) { barang ->
                        val bundle = Bundle().apply {
                            putInt("id", barang.id)
                            putString("namaBarang", barang.namaBarang)
                            putString("kategori", barang.kategori)
                            putInt("stok", barang.stok)
                            putInt("harga", barang.harga)
                            putString("deskripsi", barang.deskripsi)
                        }
                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
                    }
                    binding.rvBarang.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ApiResponse<List<Barang>>>, t: Throwable) {
                Toast.makeText(requireContext(), "Gagal memuat data: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
