package com.wazowski.forviachallenge.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.*
import com.wazowski.forviachallenge.R
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme

@Composable
fun CardImage(imageUrl: Any?, modifier: Modifier = Modifier) {
    SubcomposeAsyncImage(modifier = modifier.fillMaxHeight(),
        contentScale = ContentScale.Crop,
        model = imageUrl,
        loading = {
            LoadingIndicator(modifier = modifier.fillMaxWidth())
        },
        contentDescription = stringResource(R.string.app_name),
        error = {
            Image(
                painter = painterResource(id = R.drawable.playstore),
                contentDescription = "Error loading image"
            )
        })
}

@Composable
@Preview
fun CardImagePreview() {
    ForviaChallengeTheme {
        Surface(
            modifier = Modifier
                .height(110.dp)
                .width(110.dp)
        ) {
            CardImage(R.drawable.playstore)
        }
    }
}