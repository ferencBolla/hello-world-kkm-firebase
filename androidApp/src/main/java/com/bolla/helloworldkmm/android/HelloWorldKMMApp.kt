package com.bolla.helloworldkmm.android

import android.app.Application
import com.google.firebase.FirebaseApp

class HelloWorldKMMApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}