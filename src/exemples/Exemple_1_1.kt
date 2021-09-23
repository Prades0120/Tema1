package exemples

import java.io.File

fun main() {
    val f = File(".")
    println("Llista de fitxers i directoris del directori actual")
    println("---------------------------------------------------")
    for (e in f.list().sorted())
        println(e);
}