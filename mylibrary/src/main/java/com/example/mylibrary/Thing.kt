package com.example.mylibrary

import javax.inject.Inject

interface Thing {
    fun string(): String
}

class ThingImpl @Inject constructor() : Thing {
    override fun string() = "Hello"
}
