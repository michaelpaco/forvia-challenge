package com.wazowski.forviachallenge.presentation.error

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wazowski.forviachallenge.R
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme

@Composable
fun ErrorMessage(message: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message)
    }
}

@Preview

@Composable
fun ErrorMessagePreview() {
    ForviaChallengeTheme {
        Surface(modifier = Modifier.fillMaxHeight()) {
            ErrorMessage(message = stringResource(id = R.string.error_no_connection))
        }
    }
}