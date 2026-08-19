package dev.jdtech.jellyfin.presentation.film.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.jdtech.jellyfin.core.R
import dev.jdtech.jellyfin.presentation.theme.FindroidTheme

/** Gold star color used for the rating badge. */
val RatingBadgeColor = Color(0xFFFFC107)

/**
 * Badge showing the user's 1-10 rating for an item (e.g. "★ 8").
 * Rendered on top of the poster when the item has been rated.
 */
@Composable
fun RatingBadge(rating: Float, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = Color.Black.copy(alpha = 0.6f),
        shape = MaterialTheme.shapes.small,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = null,
                tint = RatingBadgeColor,
                modifier = Modifier.size(12.dp),
            )
            androidx.compose.material3.Text(
                text = stringResource(R.string.rating_short, rating.toInt()),
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
            )
        }
    }
}

@Composable
@Preview
private fun RatingBadgePreview() {
    FindroidTheme { RatingBadge(rating = 8f) }
}