package com.itspub.util

import io.jsonwebtoken.*

import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter
import java.util.Date

/**
 * Created by Administrator on 2017/1/11.
 */
object JwtUtils {

    fun createJWT(secret: String, id: String, issuer: String, subject: String, ttlMillis: Long, headerParameter: Map<String, Any>?): String {
        val signatureAlgorithm = SignatureAlgorithm.HS256

        val nowMillis = System.currentTimeMillis()
        val now = Date(nowMillis)

        val apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret)
        val signingKey = SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName())

        val builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey)

        if (null != headerParameter) {
            builder.setHeaderParams(headerParameter)
        }

        if (ttlMillis >= 0) {
            val expMillis = nowMillis + ttlMillis!!
            val exp = Date(expMillis)
            builder.setExpiration(exp)
        }

        return builder.compact()
    }

    fun getClaimsJws(secret: String, jwt: String): Jws<Claims> {
        try {
            val claimsJws = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                    .parseClaimsJws(jwt)
            return claimsJws
        } catch (e: Exception) {
            throw IllegalStateException("签名无效或者已经过期", e)
        }
    }
}
