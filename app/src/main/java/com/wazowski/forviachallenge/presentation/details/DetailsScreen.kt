package com.wazowski.forviachallenge.presentation.details

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.wazowski.forviachallenge.R
import com.wazowski.forviachallenge.common.*
import com.wazowski.forviachallenge.common.Constants.MAX_STARS
import com.wazowski.forviachallenge.common.Constants.PADDING_L
import com.wazowski.forviachallenge.common.Constants.PADDING_M
import com.wazowski.forviachallenge.common.Constants.PADDING_XS
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.presentation.*
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(uiState: State<DetailsUiState>, onBackPressed: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "") },
            modifier = Modifier.padding(start = PADDING_M.dp),
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
            navigationIcon = {
                FilledTonalIconButton(
                    onClick = onBackPressed, modifier = Modifier.size(44.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            })
    }) {
        Column {
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
                                .zIndex(6f)
                                .graphicsLayer {
                                    translationY = -232f
                                },

                            ) {
                            AppIconRow(icon = state.app.icon)

                            RatingRow(rating = state.app.rating)

                            AppNameAndStore(
                                appName = state.app.name, storeName = state.app.storeName
                            )

                            AppInfoRow(state.app)

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = PADDING_L.dp)
                            ) {
                                ExtendedFloatingActionButton(
                                    text = {
                                        Text(
                                            text = "Download", fontWeight = FontWeight.Bold
                                        )
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Filled.Download,
                                            contentDescription = "Download"
                                        )
                                    },
                                    onClick = { /*TODO*/ },
                                    containerColor = Color.Black,
                                    contentColor = Color.LightGray,
                                    modifier = Modifier.fillMaxWidth(0.8f)
                                )
                            }
                        }
                    }
                }

                else -> {}
            }
        }
    }
}

@Composable
private fun AppIconRow(icon: String) {
    Row(
        horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .clip(CircleShape)
                .size(128.dp),
            contentScale = ContentScale.FillHeight,
            model = icon,
            loading = {
                LoadingIndicator(modifier = Modifier.fillMaxHeight())
            },
            contentDescription = stringResource(R.string.app_name)
        )
    }
}

@Composable
private fun CentrallyArrangedColumn(
    content: @Composable () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(90.dp)) {
        content()
    }
}

@Composable
private fun AppInfoTextTItle(
    text: String
) {
    Text(text = text, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
}

@Composable
private fun AppInfoTextValue(
    text: String
) {
    Text(text = text, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Light)
}

@Composable
private fun AppInfoRow(app: ForviaApp) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = PADDING_M.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CentrallyArrangedColumn {
                AppInfoTextTItle(text = "RATING")
                AppInfoTextValue(text = "${app.rating}")
            }
            CentrallyArrangedColumn {
                AppInfoTextTItle(text = "DOWNLOADS")
                AppInfoTextValue(text = app.downloads.formatNumber())
            }
            CentrallyArrangedColumn {
                AppInfoTextTItle(text = "SIZE")
                AppInfoTextValue(text = app.size.formatFileSize())
            }
        }

        AppDetailDivider()
    }
}

@Composable
private fun RatingRow(rating: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = PADDING_XS.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        RatingBar(rating)
    }
}

@Composable
fun AppNameAndStore(appName: String, storeName: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = PADDING_XS.dp)
    ) {
        Text(
            text = appName,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "by $storeName",
            style = MaterialTheme.typography.bodySmall,
        )

        AppDetailDivider()
    }
}

@Composable
private fun AppDetailDivider() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(top = PADDING_M.dp)
    )
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