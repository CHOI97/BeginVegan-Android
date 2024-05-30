package com.example.data.mapper.auth

import com.example.data.model.auth.TokenResponse
import com.example.domain.mapper.Mapper
import com.example.domain.model.AuthToken

/** Token Mapper **
 * AccessToken = Token Type + " " + accessToken
 * RefreshToken = Token Type + " " + refreshToken
 **/

class AuthMapper: Mapper<TokenResponse,AuthToken> {
    override fun mapFromEntity(type: TokenResponse): AuthToken {
        return AuthToken(
            accessToken = "${type.tokenType} ${type.accessToken}",
            refreshToken = "${type.tokenType} ${type.refreshToken}"
        )
    }
}