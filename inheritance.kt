import java.util.Arrays
open class Vehicle {
    private var weight: Float
    
    var weightProperty: Float
    	get() = this.weight
    	set(value) { this.weight = value }
        
    constructor(weight: Float = 0.0f) {
        this.weight = weight
    }
    
    open fun printMe(): String {
        val r:String = "Vehicle - weight = " + this.weight
        //println(r)
        return r
    }
}

class Auto : Vehicle {
    private var doorsNo: Int
    
    var doorNoProperty: Int
    	get() = doorsNo
    	set(value) { this.doorsNo = value }
        
    constructor(weight: Float = 0.0f, doorsNo: Int = 0) : super(weight) {
        //this.weight = weight
        //this.weightProperty = weight
        this.doorsNo = doorsNo
    }
    
    override fun printMe(): String {
        val r:String = "Auto - weight = " + this.weightProperty + ", doorNo = "+this.doorsNo
        //println(r)
        return r
    }
}

class Plane : Vehicle {
    private var capacity: Float
    var capacityProperty: Float
    	get() = this.capacity
    	set(value) { this.capacity = value }
        
    private var enginesNo: Int
    
    constructor(weight: Float = 0.0f, capacity: Float = 0.0f, enginesNo: Int = 0) : super(weight) {
        this.capacity = capacity
        this.enginesNo = enginesNo
    }
    
    override fun printMe(): String {
        val r:String = "Plane - weight = " + this.weightProperty + ", capacity = "+this.capacity + ", enginesNo = "+this.enginesNo
        //println(r)
        return r
    }
}

fun main(args: Array<String>) {
	var vobj1:Vehicle = Vehicle(100.2f)
    var vobj2:Vehicle = Vehicle(50.0f)
    println("vobj1 = " + vobj1.printMe())
    println("vobj2 = " + vobj2.printMe())
    vobj2 = vobj1
    vobj2.weightProperty = 350.0f
    println("vobj1 = " + vobj1.printMe())
    println("vobj2 = " + vobj2.printMe())
    
    var v : Vehicle
    var a : Auto = Auto(1200.0f, 3)
    var p : Plane = Plane(11500.0f, 120.0f, 2)
    v = a
    println("v = " + v.printMe())
    v = p
    println("v = " + v.printMe())
    
    v = a
    // 250
    p = v as Plane //p = a
    p.printMe()
}