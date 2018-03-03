package sml.instructions

import sml.Instruction
import sml.Machine

class OutInstruction(label: String, val op1: Int): Instruction(label, "out") {
    override fun execute(m: Machine) {
        val value1 = m.registers.getRegister(op1)
        System.out.println(value1)
    }

    override fun toString(): String {
        return super.toString()
    }
}