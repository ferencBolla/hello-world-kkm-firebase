package com.bolla.helloworldkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform