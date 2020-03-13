package com.pedrojbbruno.urlValidator.services

import org.springframework.stereotype.Service

@Service
class UrlService {

    fun generateUrlRegex(urls: List<String>): String {
        var urlRegex: String = "("
        urls.forEachIndexed { index, url ->
            urlRegex += url.replace("*", ".*")
                    .replace("/","\\/")
                    .replace("{number}", "\\d*")
                    .replace("{string}", "[^\\/]*") + if (index == urls.size - 1) "$" else "$|" }
        urlRegex += ")"
        return urlRegex
    }

    fun validateUrl(url: String, urlRegex: String): Boolean {
        val pattern: Regex = urlRegex.toRegex()
        return pattern.matches(url)
    }

}