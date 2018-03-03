package sml

import sml.instructions.*
import java.io.File
import java.io.IOException
import java.util.Scanner
import kotlin.collections.ArrayList

/*
 * The machine language interpreter
 */
data class Machine(var pc: Int, val noOfRegisters: Int) {
    // The labels in the SML program, in the order in which
    // they appear (are defined) in the program

    val labels: Labels

    // The SML program, consisting of prog.size() instructions, each
    // of class sml.Instruction (or one of its subclasses)
    val prog: MutableList<Instruction>

    // The registers of the SML machine
    val registers: Registers

    // The program counter; it contains the index (in prog) of
    // the next instruction to be executed.

    init {
        labels = Labels()
        prog = ArrayList<Instruction>()
        registers = Registers(noOfRegisters)
    }

    /**
     * Print the program
     */
    override fun toString(): String {
        val s = StringBuffer()
        for (i in 0 until prog.size)
            s.append(prog[i].toString() + "\n")
        return s.toString()
    }

    /**
     * Execute the program in prog, beginning at instruction 0. (iterating through using program counter)
     * Precondition: the program and its labels have been stored properly.
     */
    fun execute() {
        while (pc < prog.size) {
            val ins = prog[pc++]
            ins.execute(this)
        }
    }

    fun jump(toLabel: String) {
        pc = labels.getLabels().indexOf(toLabel)
    }

    // root of files
    private val PATH = System.getProperty("user.dir") + "/"
    // input line of file
    private var line: String = ""

    /**
     * translate the small program in the file into lab (the labels) and prog (the program)
     * return "no errors were detected"
     */
    fun readAndTranslate(file: String): Boolean {
        val fileName = PATH + file // source file of SML code
        return try {
            Scanner(File(fileName)).use { sc ->
                // Scanner attached to the file chosen by the user
                labels.reset()
                prog.clear()

                // Each iteration processes line and reads the next line into line
                while (sc.hasNext()) {
                    line = sc.nextLine()
                    // Store the label in label
                    val label = scan()

                    if (label.length > 0) {
                        labels.addLabel(label)
                        prog.add(getInstruction(label))
                    }
                }
            }
            true
        } catch (ioE: IOException) {
            println("File: IO error " + ioE.message)
            false
        }
    }

    /**
     * line should consist of an MML instruction, with its label already removed.
     * Translate line into an instruction with label label and return the instruction
     */
    fun getInstruction(label: String): Instruction {
        val s1: Int // Possible operands of the instruction
        val s2: Int
        val r: Int
        val label2: String

        val ins = scan()
        return when (ins) { // replace with reflection
            "add" -> {
                r = scanInt()
                s1 = scanInt()
                s2 = scanInt()
                AddInstruction(label, r, s1, s2)
            }
            "sub" -> {
                r = scanInt()
                s1 = scanInt()
                s2 = scanInt()
                SubInstruction(label, r, s1, s2)
            }
            "mul" -> {
                r = scanInt()
                s1 = scanInt()
                s2 = scanInt()
                MulInstruction(label, r, s1, s2)
            }
            "div" -> {
                r = scanInt()
                s1 = scanInt()
                s2 = scanInt()
                MulInstruction(label, r, s1, (1 / s2))
            }
            "out" -> {
                s1 = scanInt()
                OutInstruction(label, s1)
            }
            "lin" -> {
                r = scanInt()
                s1 = scanInt()
                LinInstruction(label, r, s1)
            }
            "bnz" -> {
                r = scanInt()
                label2 = scan()
                BnzInstruction(label, r, label2)
            }
            else -> {
                NoOpInstruction(label, line)
            }
        }
    }

    /*
     * Return the first word of line and remove it from line. If there is no
     * word, return ""
     */
    private fun scan(): String {
        line = line.trim { it <= ' ' }
        if (line.length == 0)
            return ""

        var i = 0
        while (i < line.length && line[i] != ' ' && line[i] != '\t') {
            i = i + 1
        }
        val word = line.substring(0, i)
        line = line.substring(i)
        return word
    }

    /**
     * Return the first word of line as an integer. If there is
     * any error, return the maximum int
     */
    private fun scanInt(): Int {
        val word = scan()
        if (word.length == 0) {
            return Integer.MAX_VALUE
        }

        return try {
            Integer.parseInt(word)
        } catch (e: NumberFormatException) {
            Integer.MAX_VALUE
        }
    }
}
