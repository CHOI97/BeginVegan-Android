package com.example.domain.mapper

interface DBMapper<E, D> {
    fun mapFromEntity(type: E): D
    fun entityFromMap(type: D): E
}