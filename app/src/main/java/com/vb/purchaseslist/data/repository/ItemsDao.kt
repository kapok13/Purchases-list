package com.vb.purchaseslist.data.repository

import androidx.room.*
import com.vb.purchaseslist.data.model.Purchased
import com.vb.purchaseslist.data.model.Purchase
import io.reactivex.Flowable

@Dao
interface ItemsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPurchase(purchase: Purchase)


    @Delete
    fun deletePurchase(purchase: Purchase)


    @Query("DELETE FROM purchases_table")
    fun deleteAllPurchases()


    @Query("SELECT * FROM purchases_table")
    fun getPurchases(): Flowable<List<Purchase>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPurchased(purchased: Purchased)


    @Query("SELECT * FROM purchased_items_table")
    fun getPurchased(): Flowable<List<Purchased>>


}
