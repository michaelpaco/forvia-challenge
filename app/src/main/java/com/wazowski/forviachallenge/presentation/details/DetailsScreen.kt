package com.wazowski.forviachallenge.presentation.details

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wazowski.forviachallenge.common.*
import com.wazowski.forviachallenge.common.Constants.MAX_STARS
import com.wazowski.forviachallenge.presentation.*
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme

@Composable
fun DetailsScreen(uiState: State<DetailsUiState>, onBackPressed: () -> Unit) {
    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            Row {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
            when (val state = uiState.value) {
                is DetailsUiState.Success -> {
                    Column(verticalArrangement = Arrangement.spacedBy((-32).dp)) {
                        HeroImage(imageUrl = state.app.graphic)
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                    shape = RoundedCornerShape(24.dp)
                                )

                        ) {
                            AppNameAndDOwnloadsRow(
                                appName = state.app.name,
                                downloads = state.app.downloads
                            )

                            StoreNameAndSizeRow(
                                storeName = state.app.storeName,
                                size = state.app.size
                            )

                            RatingRow(rating = state.app.rating)
                        }
                    }
                }

                else -> {}
            }
        }
    }
}

@Composable
private fun RatingRow(rating: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 24.dp, top = 6.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        RatingBar(rating)

        Text(
            text = "- $rating",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(start = 2.dp)
        )
    }
}

@Composable
private fun StoreNameAndSizeRow(storeName: String, size: Long) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "by $storeName",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth(0.7f)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Storage,
                contentDescription = "Size",
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = size.formatFileSize(), style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
private fun AppNameAndDOwnloadsRow(appName: String, downloads: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = appName,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(0.7f)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 6.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Download,
                contentDescription = "Downloads",
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = downloads.formatNumber(),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
private fun RatingBar(rating: Float) {
    val fullStarCount = rating.toInt()
    val halfStar = rating - fullStarCount >= 0.4

    Row(verticalAlignment = Alignment.CenterVertically) {

        repeat(MAX_STARS) { index ->
            val icon = when {
                index < fullStarCount -> Icons.Default.Star
                index == fullStarCount && halfStar -> Icons.Default.StarHalf
                else -> Icons.Default.StarBorder
            }
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}


@Preview
@Composable
fun DetailsScreenDataPreview() {
    val uiState = remember {
        mutableStateOf(
            DetailsUiState.Success(app = allApps.first())
        )
    }

    ForviaChallengeTheme {
        DetailsScreen(uiState = uiState, {})
    }
}