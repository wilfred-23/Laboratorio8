package com.wilfredorellana.laboratorio8.data

object CharacterDb {
    private val characters = listOf(
        Character(1, "Optimus Prime", "Autobot", "Cybertronian", "Male", "Cybertron"),
        Character(2, "Bumblebee", "Autobot", "Cybertronian", "Male", "Cybertron"),
        Character(3, "Megatron", "Decepticon", "Cybertronian", "Male", "Cybertron"),
        Character(4, "Hot Rod", "Autobot", "Cybertronian", "Male", "Cybertron"),
        Character(5, "Soundwave", "Decepticon", "Cybertronian", "Male", "Cybertron"),
        Character(6, "Shockwave", "Decepticon", "Cybertronian", "Male", "Cybertron"),
        Character(7, "Starscream", "Decepticon", "Cybertronian", "Male", "Cybertron"),
        Character(8, "Arcee", "Autobot", "Cybertronian", "Female", "Cybertron"),
        Character(9, "Ironhide", "Autobot", "Cybertronian", "Male", "Cybertron"),
        Character(10, "Ratchet", "Autobot", "Cybertronian", "Male", "Cybertron")
    )

    fun getCharacters(): List<Character> = characters

    fun getCharacterById(id: Int): Character? {
        return characters.find { character ->
            character.id == id
        }
    }
}