package com.pw.pwdemo.db

import me.user.db.TaskDb

actual object Database {

    actual val taskDb: TaskDb by lazy { TaskDb(DatabaseDriverFactory().createDriver()) }
}