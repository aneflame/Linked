package ane.linked.app

import java.io.File
import java.util.Scanner
import kotlin.String

fun main() {
    val scanner = Scanner(System.`in`)
    val myFile = File("text.txt")

    if (!myFile.exists()) {
        myFile.createNewFile()
    }

    val links = myFile.readLines().toMutableList()

    menu(scanner, myFile, links)
}

fun menu(scanner: Scanner, myFile: File, links: MutableList<String>) {
    var choice: Int

    while (true) {
        println("1. Add A New Link")
        println("2. View Saved Links")
        println("3. Delete Links")
        println("4. Exit")

        print("Pick a option: ")

        val input = scanner.nextLine()
        choice = input.toIntOrNull() ?: -1

        when (choice) {
            1 -> {
                println("Waiting for your response...")

                val line = scanner.nextLine()
                links.add(line)
                myFile.appendText(line + "\n")

                println("New Link Successfully Added...")
            }
            2 -> {
                println("Viewing saved links...")
                myFile.forEachLine { println(it) }
            }
            3 -> { subMenu(scanner, myFile) }
            4 -> {
                println("Exiting...")
                return
            }
            else -> println("Invalid option, please try again.")
        }
        println()
    }
}

fun subMenu(scanner: Scanner, myFile: File){

    var subChoice = 0
    while (subChoice != 2) {
        println("1. Delete Selected Links")
        println("2. Delete All Links")
        println("3. Back")

        print("Pick an option: ")

        val input = scanner.nextLine()
        subChoice = input.toIntOrNull() ?: -1

        when (subChoice) {
            1 -> {
                if (!myFile.exists()) {
                    println("No links to delete.")
                    break
                }

                val lines = myFile.readLines()

                if (lines.isEmpty()) {
                    println("File is empty.")
                    break
                }

                println("Select link number to delete:")
                lines.forEachIndexed { index, line ->
                    println("${index + 1}. $line")
                }

                print("Enter number: ")
                val numberInput = scanner.nextLine()
                val indexToDelete = numberInput.toIntOrNull()?.minus(1)

                if (indexToDelete == null || indexToDelete !in lines.indices) {
                    println("Invalid selection.")
                    break
                }

                val updatedLines = lines.filterIndexed { index, _ ->
                    index != indexToDelete
                }

                myFile.writeText(updatedLines.joinToString("\n") + "\n")
                println("Link successfully deleted.")
            }
            2 -> {
                if (myFile.exists() && myFile.isFile) {
                    myFile.writeText("")
                    println("All Saved Links Successfully Deleted...")
                } else {
                    println("Error While Deleting File")
                }
            }
            3 -> {
                println("Back to main menu")
                break
            }
        }
    }
}