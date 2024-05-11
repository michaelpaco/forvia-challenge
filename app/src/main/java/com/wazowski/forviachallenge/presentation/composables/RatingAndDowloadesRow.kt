package com.wazowski.forviachallenge.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import com.wazowski.forviachallenge.common.*

@Composable
fun RatingAndDownloadsRow(rating: Float, downloads: Int, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 2.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Rating",
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = rating.toString(),
                style = MaterialTheme.typography.labelSmall
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Download,
                contentDescription = "Downloads",
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = downloads.formatNumber(),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}