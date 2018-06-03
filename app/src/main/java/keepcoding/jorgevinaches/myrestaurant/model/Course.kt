package keepcoding.jorgevinaches.myrestaurant.model

import java.io.Serializable

data class Course(var name: String,
                  var ingredients: List<String>,
                  var allergens: List<Allergen>?,
                  var variants: String?,
                  var price: Float,
                  var imageRes: Int): Serializable {

    override fun toString() = name
}