package com.pw.pwdemo

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}