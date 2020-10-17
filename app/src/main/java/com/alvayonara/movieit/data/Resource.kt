package com.alvayonara.movieit.data

import com.alvayonara.movieit.utils.Status

/*
    Resource berfungsi sebagai pembungkus data yang akan dikelola dan ditampilkan.
 */
data class Resource<T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null
) {

    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data)
        fun <T> error(msg: String?, data: T?): Resource<T> = Resource(Status.ERROR, data, msg)
        fun <T> loading(data: T?): Resource<T> = Resource(Status.LOADING, data)
    }
}