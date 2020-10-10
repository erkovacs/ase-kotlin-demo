import java.util.*

import javax.crypto.*
import javax.crypto.spec.*

class CryptoAES {
    private fun maybePadIv (iv: ByteArray): ByteArray {
        var newArray = iv.clone()
        while (newArray.lastIndex < 17) {
            newArray += 0x00.toByte()
        }
        
        return newArray
    }
    
    fun aesCryptoECB(inputData: ByteArray, key: ByteArray, mode: Int):ByteArray? {
        try {
            val cipher = Cipher.getInstance("AES/ECB/NoPadding")
            val secretKey = SecretKeySpec(key, "AES")
            
            if(mode == 0) {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKey) 
            }
            
            return cipher.doFinal(inputData)
        } catch(e: Exception) {
            e.printStackTrace()
        }
        
        return null
    }
    
    fun aesCryptoCBC(inputData: ByteArray, key: ByteArray, iv: ByteArray, mode: Int):ByteArray? {
        try {
            val cipher = Cipher.getInstance("AES/CBC/NoPadding")
            val secretKey = SecretKeySpec(key, "AES")
            val ivSpec = IvParameterSpec(iv)
            
            if(mode == 0) {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec)
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec) 
            }
            
            return cipher.doFinal(inputData)
        } catch(e: Exception) {
            e.printStackTrace()
        }
        
        return null
    }
}

fun main(args: Array<String>) {
    val testText = "Hey! Java/Kotlin"
    val pass =     "password#1234567"
    val iv = "1234567800000000"
    
    var c = CryptoAES()
    
    // ECB
    var encryptedTextB:ByteArray? = c.aesCryptoECB(testText.toByteArray(), pass.toByteArray(), 0)
    println( "AES/ECB/enc = \n" + Base64.getEncoder().encodeToString(encryptedTextB) )
    
    var decryptedTextB:ByteArray? = c.aesCryptoECB(encryptedTextB!!, pass.toByteArray(), 1)
    println( "AES/ECB/dec = \n" + Base64.getEncoder().encodeToString(decryptedTextB!!) + ", str = " + String(decryptedTextB!!))
    
    // CBC
    var encryptedText2B:ByteArray? = c.aesCryptoCBC(testText.toByteArray(), pass.toByteArray(), iv.toByteArray(), 0)
    println( "AES/CBC/enc = \n" + Base64.getEncoder().encodeToString(encryptedText2B) )
    
    var decryptedText2B:ByteArray? = c.aesCryptoCBC(encryptedText2B!!, pass.toByteArray(), iv.toByteArray(), 1)
    println( "AES/CBC/dec = \n" + Base64.getEncoder().encodeToString(decryptedText2B!!) + ", str = " + String(decryptedText2B!!))
}