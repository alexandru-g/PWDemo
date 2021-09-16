package com.pw.pwdemo.db

import android.content.Context
import me.user.db.TaskDb

actual object Database {

    lateinit var context: Context

    actual val taskDb: TaskDb by lazy { TaskDb(DatabaseDriverFactory(context).createDriver()) }
}