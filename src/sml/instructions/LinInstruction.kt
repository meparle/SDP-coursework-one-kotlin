package sml.instructions

import sml.Instruction
import sml.Machine

class LinInstruction(label: String, val register: Int, val value: Int) : Instruction(label, "lin") {

    override fun execute(m: Machine) {
        m.registers.setRegister(register, value)
    }

    override fun toString(): String {
        return super.toString() + " register " + register + " value is " + value
    }
}
