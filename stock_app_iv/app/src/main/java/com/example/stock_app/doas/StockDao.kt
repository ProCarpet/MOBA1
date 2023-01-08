package com.example.stock_app.doas

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.stock_app.Stock

@Dao
interface StockDao {
    @Query("SELECT * FROM stock")
    fun getAll(): List<Stock>

    @Insert
    fun insertAll(vararg stocks: Stock)

    @Delete
    fun delete(stock: Stock)
}