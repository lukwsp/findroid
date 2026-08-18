package dev.jdtech.jellyfin.film.presentation.episode

sealed interface EpisodeEvent {
    data object ItemDeleted : EpisodeEvent

    data class Error(val error: Exception) : EpisodeEvent
}