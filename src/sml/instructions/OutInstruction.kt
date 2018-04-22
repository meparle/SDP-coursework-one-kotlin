package sml.instructions

import sml.Instruction
import sml.Machine

/**
 * Describes the SML OUT instruction
 *
 */
class OutInstruction(label: String, val op1: Int): Instruction(label, "Out") {
    override fun execute(m: Machine) {
        val value1 = m.registers.getRegister(op1)
        System.out.println(value1)
    }

}