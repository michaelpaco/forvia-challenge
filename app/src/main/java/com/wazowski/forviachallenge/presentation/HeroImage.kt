package com.wazowski.forviachallenge.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.*
import com.wazowski.forviachallenge.R
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme

@Composable
fun HeroImage(imageUrl: Any?) {
    SubcomposeAsyncImage(modifier = Modifier
        .background(Color.Red)
        .fillMaxWidth()
        .heightIn(120.dp, 320.dp),
        contentScale = ContentScale.FillHeight,
        model = imageUrl,
        loading = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                CircularProgressIndicator()
            }
        },
        contentDescription = stringResource(R.string.app_name),
        error = {
            Image(
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.placeholder_hero),
                contentDescription = "Error loading image"
            )
        })
}

@Composable
@Preview
fun HeroImagePreview() {
    ForviaChallengeTheme {
        Surface(
            modifier = Modifier
                .height(110.dp)
                .width(110.dp)
        ) {
            HeroImage(R.drawable.placeholder_hero)
        }
    }
}