fun main() {
    val a: Int = 12
    val b: Int = 3
    
    var max: Int
    
    if (a > b) {
        max = a
    } else {
        max = b
    }
    
    println(max)
    
    when (max) {
        a -> println("Max is a")
        b -> println("Max is b")
        else -> println("Max is neither")
    }
    
    val arr = listOf(1, 2, 3)
    for (i in 0..arr.lastIndex) {
        println("Arr of i = " + arr[i])
    }
    
    for (obj in arr) {
        println("Item = " + obj)
    }
}