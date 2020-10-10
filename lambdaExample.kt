fun main (args: Array<String>) {
    val myLambda: (String) -> Unit = {
        s: String -> println(s)
    }
    myFun("Kotlin", myLambda)
    
    myLambda("We are learning Kotlin")
}

fun myFun(a: String, action: (String) -> Unit) {
    println("Hello / halo ")
    action(a)
}