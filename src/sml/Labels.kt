package sml

/**
 * An instance contains a list of Strings, called "labels",
 * in the order in which they were added to the list.
 */

//labels is a numbered list - as labels are executed as instructions, they should still exist
class Labels {
    private val labels: MutableList<String>

    init {
        labels = ArrayList<String>()
    }

    /**
     * Add label lab to this list and return its number in the list
     * (the first one added is number 0)
     * Precondition: the list has at most 49 entries
     */
    fun addLabel(lab: String): Int {
        labels.add(lab)
        return labels.size - 1
    }

    /**
     * copy of internal list
     */
    fun getLabels() = labels.toMutableList()

    /**
     * representation of this instance, "(label 0, label 1, ..., label (n-1))"
     * invariant: r contains the representation for labels[0..i-1]
     */
    override fun toString(): String {
        val r = StringBuilder("(")

        labels.indices.forEach {
            if (it == 0)
                r.append(labels[it])
            else
                r.append(", ").append(labels[it])
        }
        r.append(")")
        return r.toString()
    }

    /**
     * Set the number of elements in the list to 0
     */
    fun reset() {
        labels.clear()
    }
}
