package app.architect.notes.utils

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    fun hello(): String {
        return "I am all native in shared module"
    }
}
