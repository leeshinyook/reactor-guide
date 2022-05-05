package me.shinyook.reactorguide.domain

data class User (
    val username: String,
    val firstname: String,
    val lastname: String
) {
    companion object {
        val SKYLER = User("swhite", "Skyler", "White")
        val JESSE = User("jpinkman", "Jesse", "Pinkman")
        val WALTER = User("wwhite", "Walter", "White")
        val SAUL = User("sgoodman", "Saul", "Goodman")
        val MARIE = User("mschrader", "Marie", "Schrader")
        val MIKE = User("mehrmantraut", "Mike", "Ehrmantraut")
    }
    fun toUppercase(): User = User(this.username.uppercase(), this.firstname.uppercase(), this.lastname.uppercase())
}