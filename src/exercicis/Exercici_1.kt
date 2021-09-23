@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package exercicis

import java.io.File
import java.util.*

fun main() {
    println("Introdueix un directori:")
    val ent = Scanner(System.`in`)
    var file = File.listRoots()[0]
    var bucle: Boolean
    var selection: Int
    var llista: Array<File>

    do {
        llista = llistaDirectori(file)
        do {
            bucle = true
            try {
                println("Selecciona el directori:")
                selection = ent.nextInt()
                ent.nextLine()
                if (selection == -1) {
                    bucle = false
                    println("Surtint.......")
                }else if (selection == 0) {
                    if (file.parentFile != null) {
                        file = file.parentFile
                        bucle = false
                    } else {
                        println("\nNO EXISTEIS CAP DIRECTORI PARE")
                    }
                }else if (selection <-1 || selection > llista.size){
                    println("\nNO EXISTEIS CAP DIRECTORI AMB EL VALOR INTRODUIT")
                }else{
                    if (llista[selection-1].isDirectory) {
                        if (llista[selection-1].canRead()) {
                            file = llista[selection-1]
                            bucle = false
                        }else {
                            println("\nNO ES POT ACCEDIR AL FITXER\n")
                        }
                    }else{
                        println("\nNO ES POT ACCEDIR A UN ARXIU")
                    }
                }
            }catch (inputE: InputMismatchException){
                println(inputE)
                ent.nextLine()
                selection = -1
            }
        }while (bucle)
    }while (selection != -1)

}

fun llistaDirectori(file: File): Array<File> {
    val s = "Llista de fitxers i directoris del directori " + file.canonicalPath
    var contador = 0
    println(s)
    println("-".repeat(s.length))
    println("0.- Directori pare")
    for (e in file.listFiles().sorted()) {
        contador++
        println(infoArchivo(e,contador))

    }
    println("-".repeat(s.length))
    println("Introduix -1 per a surtir")
    return file.listFiles().sortedArray()
}

fun infoArchivo(file: File,contador: Int): String {
    var info = "$contador\t"
    val date = Date(file.lastModified())

    info += if (file.isDirectory)
        "d"
    else
        "-"

    info += if (file.canRead())
        "r"
    else
        "-"

    info += if (file.canWrite())
        "w"
    else
        "-"

    info += if (file.canExecute())
        "x"
    else
        "-"

    info += ("\t" + file.length() + " \t" + date + " \t" + file.name)

    return info
}