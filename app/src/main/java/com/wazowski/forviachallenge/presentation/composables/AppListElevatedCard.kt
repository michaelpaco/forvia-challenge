package com.wazowski.forviachallenge.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.dp
import com.wazowski.forviachallenge.common.Constants
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.presentation.CardImage

@Composable
fun AppListElevatedCard(app: ForviaApp) {
    ElevatedCard(modifier = Modifier.heightIn(max = 92.dp)) {
        Row {
            Column(
                modifier = Modifier
                    .padding(
                        top = Constants.PADDING_S.dp,
                        bottom = Constants.PADDING_S.dp,
                        start = Constants.PADDING_SM.dp
                    )
                    .clip(
                        RoundedCornerShape(25, 25, 25, 25)
                    )
            ) {
                CardImage(
                    imageUrl = app.icon, modifier = Modifier.size(72.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = Constants.PADDING_XS.dp, vertical = Constants.PADDING_SM.dp
                    )
                    .width(124.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = app.name.trim(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(
                        start = Constants.PADDING_XS.dp,
                        top = Constants.PADDING_XS.dp,
                        end = Constants.PADDING_XS.dp
                    )
                )

                RatingAndDownloadsRow(rating = app.rating, downloads = app.downloads)
            }
        }
    }
}