package com.wilfredorellana.laboratorio8.navigation

import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

@Serializable
object HomeRoute

@Serializable
object CharactersGraph

@Serializable
object CharactersListRoute

@Serializable
data class CharacterDetailsRoute(
    val characterId: Int
)

@Serializable
object LocationsGraph

@Serializable
object LocationsListRoute

@Serializable
data class LocationDetailsRoute(
    val locationId: Int
)

@Serializable
object ProfileRoute