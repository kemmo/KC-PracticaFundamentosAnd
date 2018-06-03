package keepcoding.jorgevinaches.myrestaurant.model

import java.io.Serializable

data class Table(var numberOfTable: Int,
                 var courses: MutableList<Course>?): Serializable {

    override fun toString() = "Mesa: $numberOfTable"
}