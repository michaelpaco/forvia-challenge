package com.wazowski.forviachallenge.presentation.composables

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import com.wazowski.forviachallenge.common.Constants.PADDING_XS
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme

@Composable
fun AppListRowCardItem(app: ForviaApp, onClick: (Int) -> Unit, modifier: Modifier = Modifier) {
    ElevatedCard(modifier = modifier
        .width(120.dp)
        .clickable {
            onClick(app.id)
        }
        .padding(horizontal = PADDING_XS.dp)) {
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
            modifier = Modifier.padding(
                start = PADDING_XS.dp,
                top = PADDING_XS.dp,
                end = PADDING_XS.dp
            )
        )

        RatingAndDownloadsRow(rating = app.rating, downloads = app.downloads)
    }
}

@Composable
@Preview
fun AppListRowItemPreview() {
    val app = allApps.first()

    ForviaChallengeTheme {
        Surface(
            modifier = Modifier
                .height(100.dp)
                .width(60.dp)
        ) {
            AppListRowCardItem(app = app, onClick = {})
        }
    }

}
