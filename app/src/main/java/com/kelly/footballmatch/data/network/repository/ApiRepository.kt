package com.kelly.footballmatch.data.network.repository

import java.net.URL

class ApiRepository {
    fun doRequest(url: String) : String {
        return URL(url).readText()
    }
}