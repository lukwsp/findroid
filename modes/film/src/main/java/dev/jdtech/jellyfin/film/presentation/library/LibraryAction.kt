package dev.jdtech.jellyfin.film.presentation.library

import dev.jdtech.jellyfin.models.FindroidItem
import dev.jdtech.jellyfin.models.SortBy
import dev.jdtech.jellyfin.models.SortOrder
import java.util.UUID

sealed interface LibraryAction {
    data class OnItemClick(val item: FindroidItem) : LibraryAction

    data object OnBackClick : LibraryAction

    data class ChangeSorting(val sortBy: SortBy, val sortOrder: SortOrder) : LibraryAction

    data class SetHighRatedOnly(val enabled: Boolean) : LibraryAction

    data class SetRating(val itemId: UUID, val rating: Int) : LibraryAction

    data class ClearRating(val itemId: UUID) : LibraryAction

    data class DeleteItem(val itemId: UUID) : LibraryAction
}
