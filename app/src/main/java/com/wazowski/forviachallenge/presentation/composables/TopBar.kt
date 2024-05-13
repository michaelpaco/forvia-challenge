package com.wazowski.forviachallenge.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wazowski.forviachallenge.common.Constants
import com.wazowski.forviachallenge.common.Constants.PADDING_XS
import com.wazowski.forviachallenge.presentation.theme.LocalCustomPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onBackPressed: () -> Unit) {
    TopAppBar(
        title = {},
        modifier = Modifier.padding(start = Constants.PADDING_M.dp),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        navigationIcon = {
            FilledTonalIconButton(
                onClick = onBackPressed,
                modifier = Modifier.size(44.dp).padding(top = PADDING_XS.dp),
                colors = IconButtonDefaults.iconButtonColors(containerColor = LocalCustomPalette.current.metadataBackgroundColor)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = LocalCustomPalette.current.metadataColor
                )
            }
        })
}