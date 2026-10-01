package com.wilfredorellana.laboratorio8.data

object CharacterDb {
    private val characters = listOf(
        Character(1, "Rick Sanchez", "Alive", "Human", "Male", "Earth (C-137)"),
        Character(2, "Morty Smith", "Alive", "Human", "Male", "Unknown"),
        Character(3, "Summer Smith", "Alive", "Human", "Female", "Earth"),
        Character(4, "Beth Smith", "Alive", "Human", "Female", "Earth"),
        Character(5, "Jerry Smith", "Alive", "Human", "Male", "Earth"),
        Character(6, "Abadango Cluster Princess", "Alive", "Alien", "Female", "Abadango"),
        Character(7, "Abradolf Lincler", "Unknown", "Human", "Male", "Earth"),
        Character(8, "Adjudicator Rick", "Dead", "Human", "Male", "Unknown"),
        Character(9, "Agency Director", "Dead", "Human", "Male", "Earth"),
        Character(10, "Alan Rails", "Dead", "Human", "Male", "Unknown")
    )

    fun getCharacters(): List<Character> = characters

    fun getCharacterById(id: Int): Character? {
        return characters.find { character ->
            character.id == id
        }
    }
}