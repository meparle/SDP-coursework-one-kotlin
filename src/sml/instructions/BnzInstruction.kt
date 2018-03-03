package sml.instructions

import sml.Instruction
import sml.Machine

/**
 * Describes the SML BNZ instruction
 * If the contents of register s1 is not zero, then make the statement labeled L2 the next one to execute
 *
 */

//currently copied from AddInstruction
//what to do if s1 = 0 no op
//if s1 =! 0, need to jump to line + reset program counter

class BnzInstruction(label: String, val op1: Int, val toLabel: String) : Instruction(label, "bnz") {

    override fun execute(m: Machine) {
        val value1 = m.registers.getRegister(op1)
        if (value1 != 0) {
            m.jump(toLabel)
        } else {
        }
    }

        override fun toString(): String {
            return super.toString() + " register " + op1 + " bnz"
        }
}

