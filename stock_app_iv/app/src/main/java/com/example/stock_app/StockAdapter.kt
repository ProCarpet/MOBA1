package com.example.stock_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
class StockAdapter(private val mStocks: MutableList<Stock>, val context: Context):RecyclerView.Adapter<StockAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        var nameTextView: TextView =  itemView.findViewById(R.id.stock_name)
        var symbolTextView: TextView = itemView.findViewById(R.id.stock_symbol)
        var priceTextView: TextView = itemView.findViewById(R.id.stock_price)

    }
    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.item_stock, parent, false)
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val stock: Stock = mStocks.get(position)
        // Set item views based on your views and data model
        val nameView = holder.nameTextView
        nameView.setText(stock.name)
        val symbolView = holder.symbolTextView
        symbolView.setText(stock.symbol)
        val priceView = holder.priceTextView
        priceView.setText(stock.value.toString())
    }

    override fun getItemCount(): Int {
        return mStocks.size
    }
}