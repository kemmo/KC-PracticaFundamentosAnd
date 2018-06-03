package keepcoding.jorgevinaches.myrestaurant.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import keepcoding.jorgevinaches.myrestaurant.R
import keepcoding.jorgevinaches.myrestaurant.model.Allergen
import keepcoding.jorgevinaches.myrestaurant.model.Course
import kotlinx.android.synthetic.main.activity_course_detail.*

class CourseDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_COURSE = "EXTRA_COURSE"

        fun intent(context: Context, course: Course): Intent {
            val intent = Intent(context, CourseDetailActivity::class.java)
            intent.putExtra(EXTRA_COURSE, course)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        val course = intent.extras.getSerializable(EXTRA_COURSE) as Course

        course_image.setImageResource(course.imageRes)
        course_title.text = course.name
        course_variants.text = "VARIANTES: $course.variants"

        var ingredients = "INGREDIENTES: \r\n"
        course.ingredients.forEach {
            ingredients = "$ingredients$it, "
        }
        course_ingredients.text = ingredients

        course.allergens?.forEach {
            when(it) {
                Allergen.MARISCO -> allergen_shellfish.visibility = View.VISIBLE
                Allergen.HUEVO -> allergen_egg.visibility = View.VISIBLE
                Allergen.GLUTEN -> allergen_gluten.visibility = View.VISIBLE
                Allergen.LACTOSA -> allergen_lactose.visibility = View.VISIBLE
                Allergen.PESCADO -> allergen_fish.visibility = View.VISIBLE
                Allergen.SOJA -> allergen_soy.visibility = View.VISIBLE
                Allergen.CASCARAS -> allergen_husks.visibility = View.VISIBLE
            }
        }
    }
}
