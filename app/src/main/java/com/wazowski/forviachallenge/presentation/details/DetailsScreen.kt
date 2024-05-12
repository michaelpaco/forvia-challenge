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
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import coil.compose.SubcomposeAsyncImage
import com.wazowski.forviachallenge.R
import com.wazowski.forviachallenge.common.*
import com.wazowski.forviachallenge.common.Constants.MAX_STARS
import com.wazowski.forviachallenge.common.Constants.PADDING_L
import com.wazowski.forviachallenge.common.Constants.PADDING_M
import com.wazowski.forviachallenge.common.Constants.PADDING_S
import com.wazowski.forviachallenge.common.Constants.PADDING_XS
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.presentation.*
import com.wazowski.forviachallenge.presentation.composables.*
import com.wazowski.forviachallenge.presentation.error.ErrorMessage
import com.wazowski.forviachallenge.presentation.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    uiState: State<DetailsUiState>,
    onCardClick: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val openAlertDialog = remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopBar {
            onBackPressed()
        }
    }) {
        when (val state = uiState.value) {
            is DetailsUiState.Success -> {
                Column(
                    modifier = Modifier
                        .background(
                            color = LocalCustomPalette.current.listBackground
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .background(
                                color = Color.Transparent,
                                shape = RoundedCornerShape(24.dp)
                            )
                    ) {
                        HeroImage(imageUrl = state.app.graphic)

                        Column(
                            modifier = Modifier
                                .offset(y = (-92).dp)
                                .background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(24.dp)
                                )
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
                                    .background(
                                        color = MaterialTheme.colorScheme.surface,
                                    )
                            ) {
                                ExtendedFloatingActionButton(
                                    text = {
                                        Text(
                                            text = stringResource(R.string.detail_screen_download_button), fontWeight = FontWeight.Bold
                                        )
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Filled.Download,
                                            contentDescription = stringResource(R.string.detail_screen_download_button)
                                        )
                                    },
                                    onClick = { openAlertDialog.value = true },
                                    containerColor = LocalCustomPalette.current.buttonBackground,
                                    contentColor = Color.LightGray,
                                    modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .padding(vertical = PADDING_L.dp)
                                )
                            }

                            RelatedAppsListRowWithBackground(
                                apps = state.relatedApps,
                            ) { app ->
                                RelatedAppElevatedCard(
                                    app = app,
                                    onClick = onCardClick
                                )
                            }
                        }
                    }
                }

                AppDialog(openAlertDialog = openAlertDialog)
            }

            is DetailsUiState.Loading -> {
                LoadingIndicator(modifier = Modifier.fillMaxSize())
            }

            is DetailsUiState.Empty -> {
                ErrorMessage("No content to show")
            }

            is DetailsUiState.Error -> {
                ErrorMessage("No internet connection")
            }
        }
    }
}

@Composable
private fun AppIconRow(icon: String) {
    val surfaceColor = MaterialTheme.colorScheme.surface

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .drawWithContent {
                drawRect(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent, surfaceColor
                        ), startY = 0f, endY = 250f
                    )
                )
                drawContent()

            }
    ) {
        if (LocalInspectionMode.current) {
            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(128.dp),
                contentScale = ContentScale.FillHeight,
                painter = painterResource(id = R.drawable.playstore),
                contentDescription = "Preview"
            )
        } else {
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = PADDING_M.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                ),
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
            .background(
                color = MaterialTheme.colorScheme.surface,
            ),
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
            .background(
                color = MaterialTheme.colorScheme.surface,
            ),
    ) {
        Text(
            text = appName,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = PADDING_XS.dp, start = PADDING_S.dp, end = PADDING_S.dp)

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
                index == fullStarCount && halfStar -> Icons.AutoMirrored.Filled.StarHalf
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
            DetailsUiState.Success(app = allApps.first(), relatedApps = allApps.subList(0, 2))
        )
    }

    ForviaChallengeTheme {
        DetailsScreen(uiState = uiState, onCardClick = {}, onBackPressed = {})
    }
}