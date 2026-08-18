package dev.jdtech.jellyfin.film.presentation.movie

sealed interface MovieEvent {
    data object ItemDeleted : MovieEvent

    data class Error(val error: Exception) : MovieEvent
}