package com.example.domain.mapper

interface Mapper<E, D> {
    fun mapFromEntity(type: E): D
}