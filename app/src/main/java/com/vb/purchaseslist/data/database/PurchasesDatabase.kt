package com.vb.purchaseslist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vb.purchaseslist.data.model.Purchased
import com.vb.purchaseslist.data.model.Purchase
import com.vb.purchaseslist.data.repository.ItemsDao

@Database(entities = [Purchase::class, Purchased::class], version = 1, exportSchema = false)
abstract class PurchasesDatabase : RoomDatabase() {


    abstract fun getItemsDao(): ItemsDao


}