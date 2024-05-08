package com.wazowski.forviachallenge.data.mappers

import com.wazowski.forviachallenge.data.remote.*
import com.wazowski.forviachallenge.domain.model.ForviaApp

fun Datasets.toForviaAppList(): List<ForviaApp> {
    return this.all.data.list.map { data ->
        ForviaApp(
            id = data.id,
            name = data.name,
            packageName = data.packageName,
            storeId = data.storeId,
            storeName = data.storeName,
            vername = data.vername,
            vercode = data.vercode,
            downloads = data.downloads,
            added = data.added,
            modified = data.modified,
            updated = data.updated,
            rating = data.rating,
            icon = data.icon,
            graphic = data.graphic,
        )
    }
}

fun ForviaAppDto.toForviaApp(): ForviaApp {
    return ForviaApp(
        id = this.id,
        name = this.name,
        packageName = this.packageName,
        storeId = this.storeId,
        storeName = this.storeName,
        vername = this.vername,
        vercode = this.vercode,
        downloads = this.downloads,
        added = this.added,
        modified = this.modified,
        updated = this.updated,
        rating = this.rating,
        icon = this.icon,
        graphic = this.graphic,
    )
}