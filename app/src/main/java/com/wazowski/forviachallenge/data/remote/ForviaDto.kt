package com.wazowski.forviachallenge.data.remote

import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class ForviaDatasetDto(
    @Json(name = "datasets") val datasets: Datasets
)

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "total") val total: Int,
    @Json(name = "offset") val offset: Int,
    @Json(name = "limit") val limit: Int,
    @Json(name = "next") val next: Int,
    @Json(name = "hidden") val hidden: Int,
    @Json(name = "list") val list: List<ForviaApp>
)

@JsonClass(generateAdapter = true)
data class Info(
    @Json(name = "status") val status: String, @Json(name = "time") val time: Time
)

@JsonClass(generateAdapter = true)
data class Time(
    @Json(name = "seconds") val seconds: Double, @Json(name = "human") val human: String
)

@JsonClass(generateAdapter = true)
data class Datasets(
    @Json(name = "all") val all: All
)

@JsonClass(generateAdapter = true)
data class All(
    @Json(name = "info") val info: Info, @Json(name = "data") val data: Data
)

@JsonClass(generateAdapter = true)
data class ForviaApp(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "package") val packageName: String,
    @Json(name = "store_id") val storeId: Int,
    @Json(name = "store_name") val storeName: String,
    @Json(name = "vername") val vername: String,
    @Json(name = "vercode") val vercode: Int,
    @Json(name = "md5sum") val md5sum: String,
    @Json(name = "apk_tags") val apkTags: List<String>,
    @Json(name = "size") val size: Int,
    @Json(name = "downloads") val downloads: Int,
    @Json(name = "pdownloads") val pdownloads: Int,
    @Json(name = "added") val added: String,
    @Json(name = "modified") val modified: String,
    @Json(name = "updated") val updated: String,
    @Json(name = "rating") val rating: Float,
    @Json(name = "icon") val icon: String,
    @Json(name = "graphic") val graphic: String?,
    @Json(name = "uptype") val uptype: String
)
