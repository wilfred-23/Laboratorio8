package com.wilfredorellana.laboratorio8.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.wilfredorellana.laboratorio8.navigation.CharacterDetailsRoute
import com.wilfredorellana.laboratorio8.navigation.CharactersGraph
import com.wilfredorellana.laboratorio8.navigation.CharactersListRoute
import com.wilfredorellana.laboratorio8.navigation.LocationDetailsRoute
import com.wilfredorellana.laboratorio8.navigation.LocationsGraph
import com.wilfredorellana.laboratorio8.navigation.LocationsListRoute
import com.wilfredorellana.laboratorio8.navigation.ProfileRoute
import com.wilfredorellana.laboratorio8.ui.screens.characterdetails.CharacterDetailsScreen
import com.wilfredorellana.laboratorio8.ui.screens.characters.CharactersScreen
import com.wilfredorellana.laboratorio8.ui.screens.locationdetails.LocationDetailsScreen
import com.wilfredorellana.laboratorio8.ui.screens.locations.LocationsScreen
import com.wilfredorellana.laboratorio8.ui.screens.profile.ProfileScreen

@Composable
fun MainScreen(
    onLogout: () -> Unit
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any {
                        it.hasRoute<CharactersGraph>()
                    } == true,
                    onClick = {
                        navController.navigate(CharactersGraph) {
                            popUpTo(
                                navController.graph.findStartDestination().id
                            ) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.People,
                            contentDescription = "Characters"
                        )
                    },
                    label = {
                        Text("Characters")
                    }
                )

                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any {
                        it.hasRoute<LocationsGraph>()
                    } == true,
                    onClick = {
                        navController.navigate(LocationsGraph) {
                            popUpTo(
                                navController.graph.findStartDestination().id
                            ) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "Locations"
                        )
                    },
                    label = {
                        Text("Locations")
                    }
                )

                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any {
                        it.hasRoute<ProfileRoute>()
                    } == true,
                    onClick = {
                        navController.navigate(ProfileRoute) {
                            popUpTo(
                                navController.graph.findStartDestination().id
                            ) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile"
                        )
                    },
                    label = {
                        Text("Profile")
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = CharactersGraph,
            modifier = Modifier.padding(innerPadding)
        ) {
            navigation<CharactersGraph>(
                startDestination = CharactersListRoute
            ) {
                composable<CharactersListRoute> {
                    CharactersScreen(
                        onCharacterClick = { characterId ->
                            navController.navigate(
                                CharacterDetailsRoute(characterId)
                            )
                        }
                    )
                }

                composable<CharacterDetailsRoute> {
                    CharacterDetailsScreen(
                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }

            navigation<LocationsGraph>(
                startDestination = LocationsListRoute
            ) {
                composable<LocationsListRoute> {
                    LocationsScreen(
                        onLocationClick = { locationId ->
                            navController.navigate(
                                LocationDetailsRoute(locationId)
                            )
                        }
                    )
                }

                composable<LocationDetailsRoute> {
                    LocationDetailsScreen(
                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }

            composable<ProfileRoute> {
                ProfileScreen(
                    onLogout = onLogout
                )
            }
        }
    }
}