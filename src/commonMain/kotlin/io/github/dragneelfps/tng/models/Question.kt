package io.github.dragneelfps.tng.models

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val id: String,
    val value: String,
)