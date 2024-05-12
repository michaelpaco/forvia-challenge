package com.wazowski.forviachallenge.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import com.wazowski.forviachallenge.common.*

enum class IconSize {
    SMALL,
    MEDIUM,
    LARGE
}

val IconSizeMap = mapOf(
    IconSize.SMALL to 12,
    IconSize.MEDIUM to 18,
    IconSize.LARGE to 24
)

@Composable
fun RatingAndDownloadsRow(
    rating: Float,
    downloads: Int,
    modifier: Modifier = Modifier,
    iconSize: IconSize = IconSize.SMALL
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 3.dp, vertical = 2.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Rating",
                modifier = Modifier.size(IconSizeMap[iconSize]!!.dp)
            )
            Text(
                text = rating.toString(),
                style = MaterialTheme.typography.labelMedium
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Download,
                contentDescription = "Downloads",
                modifier = Modifier.size(IconSizeMap[iconSize]!!.dp)
            )
            Text(
                text = downloads.formatNumber(),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}