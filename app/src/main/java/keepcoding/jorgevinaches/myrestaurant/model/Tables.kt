package keepcoding.jorgevinaches.myrestaurant.model

object Tables {

    private val tables: List<Table> = listOf(
            Table(1, mutableListOf(Courses[0], Courses[1])),
            Table(2, mutableListOf(Courses[2])),
            Table(3, mutableListOf(Courses[3], Courses[4])),
            Table(4, mutableListOf()),
            Table(5, mutableListOf(Courses[3], Courses[2])),
            Table(6, mutableListOf(Courses[2])),
            Table(7, mutableListOf(Courses[1], Courses[4], Courses[3])),
            Table(8, mutableListOf())
    )

    val count
        get() = tables.size

    fun toArray() = tables.toTypedArray()

    operator fun get(index: Int) = tables[index]

    fun getIndex(table: Table) = tables.indexOf(table)

    fun addCourseToTable(course: Course, table: Table) {
        val i = getIndex(table)
        tables[i].courses!!.add(course)
    }

}