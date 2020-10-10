import java.math.BigInteger
import java.security.MessageDigest
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.SecureRandom
import java.security.Signature

open class SHA256Hasher {
    private val docToHash: String
    
    constructor (docToHash: String) {
        this.docToHash = docToHash
    }
    
    public fun hash(): ByteArray {
        return MessageDigest
            .getInstance("SHA-256")
            .digest(this.docToHash.toByteArray())
    }
}

open class ECDSASigner {
    private val priv: PrivateKey
    private val pub: PublicKey
    private val docToSign: String
    
    constructor (docToSign: String) {
        val keyGen = KeyPairGenerator.getInstance("EC")
    	val random = SecureRandom.getInstance("SHA1PRNG")
        keyGen.initialize(256, random)
        val pair = keyGen.generateKeyPair();
        this.priv = pair.getPrivate()
        this.pub = pair.getPublic()
        this.docToSign = docToSign
    }
    
    public fun sign (): ByteArray{
        val dsa: Signature = Signature.getInstance("SHA1withECDSA")
        dsa.initSign(this.priv)
        val bytes: ByteArray = this.docToSign.toByteArray()
        dsa.update(bytes)
        val signature = dsa.sign()
        return signature
    }
}

fun main () {
    val name = "Kovacs Erik Robert"
    
    // Hash
    val hasher = SHA256Hasher(name)
    val hash = hasher.hash()
    
    for (b in hash) {
        print("%02x".format(b))
    }
    println()
    
    // Procedural port of original code
    val keyGen: KeyPairGenerator = KeyPairGenerator.getInstance("EC")
    val random: SecureRandom = SecureRandom.getInstance("SHA1PRNG")

    keyGen.initialize(256, random)

    val pair: KeyPair = keyGen.generateKeyPair()
    val priv: PrivateKey = pair.getPrivate()
    val pub: PublicKey = pair.getPublic()

    /*
     * Create a Signature object and initialize it with the private key
     */

    val dsa: Signature = Signature.getInstance("SHA1withECDSA")
	dsa.initSign(priv)

    val bytes: ByteArray = name.toByteArray()
    dsa.update(bytes)

    /*
     * Now that all the data to be signed has been read in, generate a
     * signature for it
     */

    val signature: ByteArray = dsa.sign()
    println("Signature: " + BigInteger(1, signature).toString(16))
    
    // OO Version
    val signer = ECDSASigner(name)
    val signature2 = signer.sign()
    println("Signature2: " + BigInteger(1, signature2).toString(16))
}    