package ane.linked.app

import java.io.File
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    var choice = 0
    val filename = "text.txt"
    val myFile = File(filename)

    while (choice != 4) {
        println("1. Add A New Link")
        println("2. View Saved Links")
        println("3. Delete All Saved Links")
        println("4. Exit")

        print("Pick a option: ")

        val input = scanner.nextLine()
        choice = input.toIntOrNull() ?: -1

        when (choice) {
            1 -> {
                println("Waiting for your response...")

                val line = scanner.nextLine()
                myFile.appendText(line + "\n")

                println("New Link Successfully Added...")
            }
            2 -> {
                println("Viewing saved links...")
                myFile.forEachLine { println(it) }
            }
            3 -> {
                if (myFile.exists() && myFile.isFile) {
                    myFile.delete()
                    println("All Saved Links Successfully Deleted...")
                } else {
                    println("Error While Deleting File")
                }
            }
            4 -> {
                println("Exiting...")
                return
            }
            else -> println("Invalid option, please try again.")
        }
        println()
    }
}
