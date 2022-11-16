package com.example.stock_app.model

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon
import com.example.stock_app.Stock

class VolleyStockRepository(context: Context) {
    private val url = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=IBM&apikey=MO2GTHMHDVNW9EJK"

    private val queue = Volley.newRequestQueue(context)

    fun getData(callback: (Stock) -> Unit){
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val result = Klaxon().parse<StockService>(response)
                val stock = Stock(result?.globalQuote?.symbol?: "",
                    result?.globalQuote?.symbol?:"",
                    result?.globalQuote?.price?.toDouble()?:0.0)
                // does it need a mutable list?
                callback.invoke(stock)
            },
            {callback.invoke(Stock("error","error",0.0))})

        // add the rest to the reqeustQueue
        queue.add(stringRequest)
    }

}