package dev.jdtech.jellyfin.film.presentation.show

sealed interface ShowEvent {
    data object ItemDeleted : ShowEvent

    data class Error(val error: Exception) : ShowEvent
}