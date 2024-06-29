package com.app.academy.utils

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
