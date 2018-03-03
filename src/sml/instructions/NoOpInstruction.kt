package sml.instructions

import sml.Instruction
import sml.Machine

class NoOpInstruction(label: String, val line:String): Instruction(label, "NoOp") {
    override fun execute(m: Machine) {
    }

    override fun toString(): String {
        return super.toString() + line
    }
}