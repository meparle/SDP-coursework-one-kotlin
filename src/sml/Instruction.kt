package sml

/**
 * Constructor: an instruction with label l and opcode op
 * (op must be an operation of the language)
 *  = the representation "label: opcode" of this sml.Instruction
 */
abstract class Instruction(protected var label: String, protected var opcode: String) {

    override fun toString(): String {
        return label + ": " + opcode
    }

    /**
     * Execute this instruction on machine m.
     */
    abstract fun execute(m: Machine)
}
