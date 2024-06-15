package app.architect.notes.utils

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
