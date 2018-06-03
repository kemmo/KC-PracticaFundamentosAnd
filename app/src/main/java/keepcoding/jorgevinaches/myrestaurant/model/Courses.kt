package keepcoding.jorgevinaches.myrestaurant.model

import keepcoding.jorgevinaches.myrestaurant.R

object Courses {

    private val courses: List<Course> = listOf(
            Course("Macarrones", listOf("Pasta", "Tomate", "Carne"), listOf(Allergen.GLUTEN, Allergen.LACTOSA), null, 10.5f, R.drawable.macas),
            Course("Arroz", listOf("Arroz", "Pollo"), listOf(Allergen.GLUTEN, Allergen.CASCARAS), null, 12.8f, R.drawable.arroz),
            Course("Guisantes", listOf("Guisantes", "Jamon"), listOf(Allergen.HUEVO), null, 8.2f, R.drawable.guisantes),
            Course("Garbanzos", listOf("Garbanzos", "Soja", "Carne"), listOf(Allergen.CASCARAS, Allergen.SOJA), null, 9.9f, R.drawable.garbanzos),
            Course("Gambas", listOf("Gambas", "Mahonesa"), listOf(Allergen.HUEVO, Allergen.LACTOSA, Allergen.MARISCO), null, 15.8f, R.drawable.gambas)
    )

    fun toArray() = courses.toTypedArray()

    operator fun get(index: Int) = courses[index]
}