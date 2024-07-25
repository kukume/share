package me.kuku.share.utils

import java.security.MessageDigest

object MD5Utils {

    fun toMD5(inStr: String): String {
        val charArray = inStr.toCharArray()
        val byteArray = ByteArray(charArray.size)
        for (i in charArray.indices) {
            byteArray[i] = charArray[i].code.toByte()
        }
        return toMD5(byteArray)
    }

    fun toMD5(byteArray: ByteArray): String {
        val md5 = MessageDigest.getInstance("MD5")
        val md5Bytes = md5.digest(byteArray)
        val hexValue = StringBuilder()
        for (i in md5Bytes.indices) {
            val `val` = md5Bytes[i].toInt() and 0xff
            if (`val` < 16) {
                hexValue.append("0")
            }
            hexValue.append(Integer.toHexString(`val`))
        }
        return hexValue.toString()
    }

}