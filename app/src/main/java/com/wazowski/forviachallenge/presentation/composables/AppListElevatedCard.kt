package com.wazowski.forviachallenge.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wazowski.forviachallenge.common.*
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.presentation.CardImage
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme

@Composable
fun AppListElevatedCard(app: ForviaApp, onClick: (Int) -> Unit) {
    ElevatedCard(modifier = Modifier
        .heightIn(max = 112.dp)
        .clickable { onClick(app.id) }
        .shadow(
            elevation = 12.dp
        )) {
        Row {
            Column(
                modifier = Modifier
                    .padding(
                        top = Constants.PADDING_S.dp,
                        bottom = Constants.PADDING_S.dp,
                        start = Constants.PADDING_SM.dp
                    )
                    .clip(
                        RoundedCornerShape(12, 12, 12, 12)
                    )
            ) {
                CardImage(
                    imageUrl = app.icon, modifier = Modifier.size(92.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = Constants.PADDING_XS.dp, vertical = Constants.PADDING_SM.dp
                    )
                    .width(138.dp)
                    .fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = app.name.trim(),
                    style = MaterialTheme.typography.labelLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(
                        start = Constants.PADDING_XS.dp,
                        top = Constants.PADDING_XS.dp,
                        end = Constants.PADDING_XS.dp
                    )
                )

                RatingAndDownloadsRow(
                    rating = app.rating,
                    downloads = app.downloads,
                    iconSize = IconSize.LARGE
                )
            }
        }
    }
}

@Preview
@Composable
fun AppListElevatedCardPreview() {
    ForviaChallengeTheme {
        Surface {
            AppListElevatedCard(app = allApps.first(), onClick = {})
        }
    }
}