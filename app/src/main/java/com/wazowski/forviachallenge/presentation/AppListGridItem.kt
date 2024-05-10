package com.wazowski.forviachallenge.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wazowski.forviachallenge.R
import com.wazowski.forviachallenge.common.*
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme

@Composable
fun AppListGridItem(app: ForviaApp, onClick: (Int) -> Unit, modifier: Modifier = Modifier) {
    Card(elevation = CardDefaults.cardElevation(
        defaultElevation = 4.dp
    ), modifier = modifier
        .fillMaxWidth()
        .clickable {
            onClick(app.id)
        }) {
        Column(
            modifier = Modifier.heightIn(min = 100.dp, max = 110.dp),
        ) {
            if (LocalInspectionMode.current) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.playstore),
                    contentDescription = "Preview"
                )
            } else {
                CardImage(imageUrl = app.icon)
            }
        }

        Text(
            text = app.name.trim(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(start = 4.dp, top = 4.dp, end = 4.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 2.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Rating",
                    modifier = Modifier.size(14.dp)
                )
                Text(text = app.rating.toString(), style = MaterialTheme.typography.labelSmall)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Download,
                    contentDescription = "Downloads",
                    modifier = Modifier.size(12.dp)
                )
                Text(
                    text = app.downloads.formatNumber(), style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
@Preview
fun AppListGridItemPreview() {
    val app = allApps.first()

    ForviaChallengeTheme {
        Surface(
            modifier = Modifier
                .height(100.dp)
                .width(60.dp)
        ) {
            AppListGridItem(app = app, onClick = {})
        }
    }

}