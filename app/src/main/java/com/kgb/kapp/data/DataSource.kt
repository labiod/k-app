package com.kgb.kapp.data

interface DataSource<Model : Data> {
    fun confirmedChanges(model: Model)

    fun retrieveData(): List<Model>

    fun retrieveData(where: String): List<Model>

    fun count(): Int

    operator fun get(position: Int): Model

    fun getItemId(position: Int): Long
}