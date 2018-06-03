package keepcoding.jorgevinaches.myrestaurant.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import keepcoding.jorgevinaches.myrestaurant.R
import keepcoding.jorgevinaches.myrestaurant.model.Allergen
import keepcoding.jorgevinaches.myrestaurant.model.Course
import keepcoding.jorgevinaches.myrestaurant.model.Table
import keepcoding.jorgevinaches.myrestaurant.model.Tables
import kotlinx.android.synthetic.main.activity_add_course.*
import kotlinx.android.synthetic.main.activity_course_detail.*

class AddCourseActivity : AppCompatActivity() {

    companion object {
        val EXTRA_COURSE = "EXTRA_COURSE"
        val EXTRA_TABLE = "EXTRA_TABLE"

        fun intent(context: Context, course: Course, table: Table): Intent {
            val intent = Intent(context, AddCourseActivity::class.java)
            intent.putExtra(EXTRA_COURSE, course)
            intent.putExtra(EXTRA_TABLE, table)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        val course = intent.extras[EXTRA_COURSE] as Course
        val table = intent.extras[EXTRA_TABLE] as Table

        course_variants_edit.visibility = View.VISIBLE

        course_image.setImageResource(course.imageRes)
        course_title.text = course.name
        course_ingredients.text = course.ingredients[0]

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

        cancel_button.setOnClickListener {
            finish()
        }

        save_button.setOnClickListener {
            val variant = ""+course_variants_edit.text
            course.variants = variant
            Tables.addCourseToTable(course, table)
            finish()
        }
    }
}
