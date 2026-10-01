package com.wilfredorellana.laboratorio8.data

object LocationDb {
    private val locations = listOf(
        Location(1, "Autobot City", "City", "Earth"),
        Location(2, "Chicago", "City", "Earth"),
        Location(3, "Machu Picchu", "Historic site", "Earth"),
        Location(4, "Cybertron", "Planet", "Prime Universe"),
        Location(5, "Mission City", "City", "Earth"),
        Location(6, "Egypt", "Country", "Earth"),
        Location(7, "Hong Kong", "City", "Earth"),
        Location(8, "Hoover Dam", "Military base", "Earth"),
        Location(9, "Moon Base One", "Lunar base", "Earth's Moon"),
        Location(10, "The Nemesis", "Decepticon spaceship", "Deep space"),
        Location(11, "Stonehenge, England", "Historic monument", "Earth")
    )

    fun getLocations(): List<Location> = locations

    fun getLocationById(id: Int): Location? {
        return locations.find { location ->
            location.id == id
        }
    }
}