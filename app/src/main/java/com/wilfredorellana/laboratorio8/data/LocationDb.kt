package com.wilfredorellana.laboratorio8.data

object LocationDb {
    private val locations = listOf(
        Location(1, "Earth (C-137)", "Planet", "Dimension C-137"),
        Location(2, "Abadango", "Cluster", "Unknown"),
        Location(3, "Citadel of Ricks", "Space station", "Unknown"),
        Location(4, "Worldender's lair", "Planet", "Unknown"),
        Location(5, "Anatomy Park", "Microverse", "Dimension C-137"),
        Location(6, "Interdimensional Cable", "TV", "Unknown"),
        Location(7, "Immortality Field Resort", "Resort", "Unknown"),
        Location(8, "Post-Apocalyptic Earth", "Planet", "Post-Apocalyptic Dimension"),
        Location(9, "Purge Planet", "Planet", "Replacement Dimension"),
        Location(10, "Venzenulon 7", "Planet", "Unknown")
    )

    fun getLocations(): List<Location> = locations

    fun getLocationById(id: Int): Location? {
        return locations.find { location ->
            location.id == id
        }
    }
}