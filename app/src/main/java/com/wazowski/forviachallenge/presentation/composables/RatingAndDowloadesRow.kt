package com.wazowski.forviachallenge.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wazowski.forviachallenge.common.*
import com.wazowski.forviachallenge.common.Constants.PADDING_XS
import com.wazowski.forviachallenge.presentation.theme.*

enum class IconSize {
    SMALL, MEDIUM, LARGE
}

val IconSizeMap = mapOf(
    IconSize.SMALL to 12, IconSize.MEDIUM to 18, IconSize.LARGE to 24
)

@Composable
fun RatingAndDownloadsRow(
    rating: Float,
    downloads: Int,
    modifier: Modifier = Modifier,
    iconSize: IconSize = IconSize.SMALL
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 3.dp, vertical = 2.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Rating",
                modifier = Modifier.size(IconSizeMap[iconSize]!!.dp),
                tint = LocalCustomPalette.current.metadataColor
            )
            Text(
                text = rating.toString(),
                style = MaterialTheme.typography.labelSmall,
                color = LocalCustomPalette.current.metadataColor
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier
                .background(
                    color = LocalCustomPalette.current.metadataBackgroundColor,
                    shape = RoundedCornerShape(50)
                )
                .widthIn(48.dp)
                .padding(all = PADDING_XS.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Download,
                contentDescription = "Downloads",
                modifier = Modifier.size(IconSizeMap[iconSize]!!.dp),
                tint = LocalCustomPalette.current.metadataColor
            )
            Text(
                text = downloads.formatNumber(),
                style = MaterialTheme.typography.labelSmall,
                color = LocalCustomPalette.current.metadataColor
            )
        }
    }
}

@Preview
@Composable
fun RatingAndDownloadsRowPreview() {
    ForviaChallengeTheme {
        Surface(modifier = Modifier.width(112.dp)) {
            RatingAndDownloadsRow(rating = 4.09f, downloads = 8)
        }
    }
}