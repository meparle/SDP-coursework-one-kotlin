package sml

/**
 * This class ....
 *
 * An instance contains 32 registers and methods to access and change them
 *
 * @author someone
 */

data class Registers(val NUMBEROFREGISTERS: Int) {
    val registers: IntArray = IntArray(NUMBEROFREGISTERS)

    // Constructor: an instance whose registers are set to 0

    init {
        for (i in registers.indices) {
            registers[i] = 0
        }
    }

    /**
     * Set register i to v.
     * Precondition: 0 <= i <= NUMBEROFREGISTERS
     */
    fun setRegister(i: Int, v: Int) {
        registers[i] = v
    }

    fun getRegister(i: Int): Int {
        return registers[i]
    }

    override fun toString(): String {
        return "sml.sml.Registers(registers=" + java.util.Arrays.toString(this.registers) + ")"
    }

}
