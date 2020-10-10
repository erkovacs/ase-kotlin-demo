class MyClass {
    private var name: String = "Hardcoded name"
    
    fun printName () {
        println("MyClass.printName=" + this.name)
    }
}

class OtherClass (name: String? = null) {
    private var name: String? = name
    
    init {
    }
    
    fun printName () {
        println("OtherClass.printName=" + this.name)
    }
}

class MyTime {
    val h: Int
    val m: Int
    val s: Int
     
    constructor() {
        this.h = 0
        this.m = 0
        this.s = 0
    }
    
    constructor(other: MyTime) {
        this.h = other.h
        this.m = other.m
        this.s = other.s
    }
    
    constructor(h: Int, m: Int, s: Int) {
    	this.h = h
        this.m = m
        this.s = s
    }
    
    override fun toString() : String {
        return "h=%dm=%ds=%d".format(this.h, this.m, this.s)
    }
    
    fun equals(other: MyTime) : Boolean {
        return other.h == this.h && other.m == this.m && other.s == this.s
    }
}

fun main() {
    val myClass = MyClass()
    myClass.printName()
    val otherClass = OtherClass("Test 2")
    otherClass.printName()
    val time = MyTime();
    val time2 = MyTime(1, 2, 3);
    println(time)
    println(time2)
    val time2Copy = time2
    val time2Clone = MyTime(time2)
    println(time2Copy)
    println(time2Clone)
    println(time2Clone.equals(time2Copy))
    
    var t4: MyTime?
    t4 = MyTime(10, 10, 10)
    println(t4?.toString())
    t4 = null
    println(t4?.toString())
}