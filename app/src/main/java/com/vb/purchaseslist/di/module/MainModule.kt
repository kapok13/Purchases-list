package com.vb.purchaseslist.di.module

import android.content.Context
import androidx.room.Room
import com.vb.purchaseslist.data.database.PurchasesDatabase
import com.vb.purchaseslist.data.repository.ItemsDao
import com.vb.purchaseslist.data.repository.PurchasesItemsRepo
import com.vb.purchaseslist.data.repository.PurchasesItemsRepoImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule(private val context: Context) {


    @Provides
    @Singleton
    fun provideContext(): Context = context


    @Provides
    @Singleton
    fun provideDatabase(context: Context): PurchasesDatabase =
        Room.databaseBuilder(context, PurchasesDatabase::class.java, "purchases database")
            .build()


    @Provides
    @Singleton
    fun provideItemsDao(purchasesDatabase: PurchasesDatabase): ItemsDao =
        purchasesDatabase.getItemsDao()


    @Provides
    @Singleton
    fun providePurchasesItemsRepo(itemsDao: ItemsDao): PurchasesItemsRepo =
        PurchasesItemsRepoImpl(itemsDao)


}