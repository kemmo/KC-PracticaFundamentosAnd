package keepcoding.jorgevinaches.myrestaurant.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import keepcoding.jorgevinaches.myrestaurant.R
import keepcoding.jorgevinaches.myrestaurant.fragment.CourseListFragment
import keepcoding.jorgevinaches.myrestaurant.fragment.OnCourseSelectedListener
import keepcoding.jorgevinaches.myrestaurant.model.Course
import keepcoding.jorgevinaches.myrestaurant.model.Courses
import keepcoding.jorgevinaches.myrestaurant.model.Table

class MainCoursesListActivity : AppCompatActivity(), OnCourseSelectedListener {

    companion object {
        val EXTRA_TABLE = "EXTRA_TABLE"

        fun intent(context: Context, table: Table): Intent {
            val intent = Intent(context, MainCoursesListActivity::class.java)
            intent.putExtra(EXTRA_TABLE, table)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_courses_list)

        if (findViewById<ViewGroup>(R.id.course_list_fragment) != null) {
                supportFragmentManager.beginTransaction()
                        .add(R.id.course_list_fragment, CourseListFragment.newInstance(Courses.toArray()))
                        .commit()

        }
    }

    override fun onCourseSelected(course: Course, position: Int) {
        val intent = AddCourseActivity.intent(this, course, intent.extras[EXTRA_TABLE] as Table)
        startActivity(intent)
    }
}
