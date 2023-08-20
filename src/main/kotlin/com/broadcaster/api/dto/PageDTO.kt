package com.broadcaster.api.dto

class PageDTO {
    var page: Int = 0
        set(value) {
            field = if (0 < value) value - 1 else 0
        }
    var size: Int = 10
}