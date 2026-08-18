package dev.jdtech.jellyfin.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.jdtech.jellyfin.core.R as CoreR

private val StarColor = Color(0xFFFFC107)

val RatingStarColor = StarColor

/**
 * Bottom sheet for rating an item (1-10 stars) and optionally deleting it from the server.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingSheet(
    itemName: String,
    currentRating: Float?,
    onRate: (Int) -> Unit,
    onClearRating: () -> Unit,
    onDelete: () -> Unit,
    onDismiss: () -> Unit,
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        RatingSheetContent(
            itemName = itemName,
            currentRating = currentRating,
            onRate = onRate,
            onClearRating = onClearRating,
            onDelete = onDelete,
        )
    }
}

/**
 * Rating content usable both inside [RatingSheet] (Compose bottom sheet) and inside
 * a classic View-based BottomSheetDialog (e.g. from the player Activity).
 */
@Composable
fun RatingSheetContent(
    itemName: String,
    currentRating: Float?,
    onRate: (Int) -> Unit,
    onClearRating: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var showDeleteConfirmation by remember { mutableStateOf(false) }
    val currentRatingInt = currentRating?.toInt()

    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = itemName,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            text =
                currentRatingInt?.let {
                    stringResource(CoreR.string.rating_value, it)
                } ?: stringResource(CoreR.string.rating_none),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 8.dp),
        )
        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            for (star in 1..10) {
                Icon(
                    painter = painterResource(CoreR.drawable.ic_star),
                    contentDescription =
                        stringResource(CoreR.string.rating_star_description, star),
                    modifier =
                        Modifier
                            .size(28.dp)
                            .clickable { onRate(star) },
                    tint =
                        if (currentRatingInt != null && star <= currentRatingInt) {
                            StarColor
                        } else {
                            MaterialTheme.colorScheme.outline
                        },
                )
            }
        }
        if (currentRatingInt != null) {
            TextButton(
                onClick = onClearRating,
                modifier = Modifier.padding(top = 8.dp),
            ) {
                Text(stringResource(CoreR.string.rating_clear))
            }
        }
        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
        OutlinedButton(
            onClick = { showDeleteConfirmation = true },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Icon(
                painter = painterResource(CoreR.drawable.ic_trash),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error,
            )
            Text(
                text = stringResource(CoreR.string.rating_delete),
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 8.dp),
            )
        }
    }

    if (showDeleteConfirmation) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = { Text(stringResource(CoreR.string.rating_delete_title, itemName)) },
            text = { Text(stringResource(CoreR.string.rating_delete_body)) },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteConfirmation = false
                        onDelete()
                    },
                ) {
                    Text(
                        text = stringResource(CoreR.string.rating_delete_confirm),
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirmation = false }) {
                    Text(stringResource(CoreR.string.cancel))
                }
            },
        )
    }
}