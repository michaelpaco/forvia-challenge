package com.wazowski.forviachallenge.domain.forvia

data class ForviaApp(
    val id: Int,
    val name: String,
    val packageName: String,
    val storeId: Int,
    val storeName: String,
    val vername: String,
    val vercode: Int,
//    val md5sum: String,
//    val apkTags: List<String>,
//    val size: Int,
    val downloads: Int,
//    val pdownloads: Int,
    val added: String,
    val modified: String,
    val updated: String,
    val rating: Float,
    val icon: String,
    val graphic: String?,
//    val uptype: String
)
