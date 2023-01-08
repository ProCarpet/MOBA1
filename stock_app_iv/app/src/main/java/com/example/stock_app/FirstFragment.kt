package com.example.stock_app

import android.content.Context
import android.os.Bundle
import android.util.Base64.encodeToString
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import com.example.stock_app.databinding.FragmentFirstBinding
import com.example.stock_app.model.VolleyStockRepository


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    //https://guides.codepath.com/android/using-the-recyclerview
    //https://stackoverflow.com/questions/61846953/android-kotlin-creating-recyclerview-in-fragment
    val items = mutableListOf(
        Stock("Apple", "AAPL", 115.69),
        Stock("Microsoft", "MSFT", 214.36),
        Stock("Google", "GOOGL", 1519.45),
        Stock("Salesforce", "CRM", 255.52),
        Stock("Facebook", "FB", 260.02),
        Stock("Amazon", "AMZN", 3201.86),
        Stock("eBay", "EBAY", 54.05),
        Stock("Twitter", "TWTR", 45.41),
        Stock("Snapchat", "SNAP", 28.11))



    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = StockAdapter(items, requireContext()) //
        binding.rvStock.adapter = adapter //

        binding.rvStock.setLayoutManager(LinearLayoutManager(requireContext()))

        binding.itemAddButton.setOnClickListener {  view ->
            var stock = binding.stockInput.text.toString()
            var symbol = binding.symbolInput.text.toString()
            items.add(Stock(stock,symbol,0.0))
            binding.rvStock.adapter = StockAdapter(items,requireContext())
        }

    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}