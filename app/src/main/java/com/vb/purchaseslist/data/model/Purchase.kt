package com.vb.purchaseslist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchases_table")
data class Purchase(val name: String?, val image: String?) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
