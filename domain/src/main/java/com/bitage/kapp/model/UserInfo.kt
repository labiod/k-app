package com.bitage.kapp.model

class UserInfo(info: Map<UserInfoType, String>) {
    private val _info = info.toMutableMap()

    fun get(infoName: UserInfoType): String? = _info[infoName]

    fun put(infoName: UserInfoType, value: String) {
        _info[infoName] = value
    }

    fun <R> map(transform: (Map.Entry<UserInfoType, String>) -> R): List<R> {
        return _info.mapTo(ArrayList<R>(_info.size), transform)
    }

    companion object {
        val EMPTY = UserInfo(mapOf())
    }
}

enum class UserInfoType {
    USERNAME
}