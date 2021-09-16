package com.pw.pwdemo.android

import android.app.Application
import com.pw.pwdemo.db.Database

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Database.context = this
    }
}