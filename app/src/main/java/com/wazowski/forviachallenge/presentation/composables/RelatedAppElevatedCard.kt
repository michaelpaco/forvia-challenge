package com.wazowski.forviachallenge.presentation.composables

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wazowski.forviachallenge.R
import com.wazowski.forviachallenge.common.*
import com.wazowski.forviachallenge.common.Constants.PADDING_S
import com.wazowski.forviachallenge.common.Constants.PADDING_SM
import com.wazowski.forviachallenge.common.Constants.PADDING_XS
import com.wazowski.forviachallenge.domain.model.ForviaApp
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme

@Composable
fun RelatedAppElevatedCard(app: ForviaApp, onClick: (Int) -> Unit) {
    ElevatedCard(modifier = Modifier
        .clickable { onClick(app.id) }
        .shadow(
            elevation = 12.dp
        )) {

        Column(modifier = Modifier.padding(horizontal = PADDING_SM.dp, vertical = PADDING_S.dp)) {
            Column(
                modifier = Modifier
                    .height(92.dp)
                    .clip(
                        RoundedCornerShape(12, 12, 12, 12)
                    )
            ) {
                if (LocalInspectionMode.current) {
                    Image(
                        modifier = Modifier.fillMaxHeight(),
                        painter = painterResource(id = R.drawable.playstore),
                        contentDescription = "Preview"
                    )
                } else {
                    CardImage(imageUrl = app.icon, contentScale = ContentScale.Fit)
                }
            }
            Column(
                modifier = Modifier
                    .padding(
                        vertical = PADDING_XS.dp
                    )
                    .width(92.dp), verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = app.name,
                    style = MaterialTheme.typography.labelLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(
                        start = PADDING_XS.dp,
                        top = PADDING_XS.dp,
                        end = PADDING_XS.dp
                    )
                )

                RatingAndDownloadsRow(
                    rating = app.rating,
                    downloads = app.downloads,
                    modifier = Modifier.padding(top = PADDING_XS.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun RelatedAppElevatedCardPreview() {
    ForviaChallengeTheme {
        Surface {
            RelatedAppElevatedCard(app = allApps.first(), onClick = {})
        }
    }
}