package com.wazowski.forviachallenge.domain.model

import androidx.room.*

@Entity(tableName = "apps")
data class ForviaApp(
    @PrimaryKey val id: Int,
    val name: String,
    val packageName: String,
    val storeId: Int,
    val storeName: String,
    val vername: String,
    val vercode: Int,
    val size: Int,
    val downloads: Int,
    val added: String,
    val modified: String,
    val updated: String,
    val rating: Float,
    val icon: String,
    val graphic: String?,
)
