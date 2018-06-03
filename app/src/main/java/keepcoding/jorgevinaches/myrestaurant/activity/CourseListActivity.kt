package keepcoding.jorgevinaches.myrestaurant.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import keepcoding.jorgevinaches.myrestaurant.R
import keepcoding.jorgevinaches.myrestaurant.fragment.CourseListByTableFragment
import android.app.Activity
import keepcoding.jorgevinaches.myrestaurant.fragment.OnCourseSelectedListener
import keepcoding.jorgevinaches.myrestaurant.fragment.OnTableSelectedListener
import keepcoding.jorgevinaches.myrestaurant.fragment.TableListFragment
import keepcoding.jorgevinaches.myrestaurant.model.Course
import keepcoding.jorgevinaches.myrestaurant.model.Table

class CourseListActivity : AppCompatActivity(), OnTableSelectedListener, OnCourseSelectedListener {

    companion object {
        val EXTRA_TABLE = "EXTRA_TABLE"

        fun intent(context: Context, tableIndex: Int): Intent {
            val intent = Intent(context, CourseListActivity::class.java)
            intent.putExtra(EXTRA_TABLE, tableIndex)

            return intent
        }
    }

    var lastTableIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_list)

        if (findViewById<ViewGroup>(R.id.table_list_fragment) != null) {
            if (supportFragmentManager.findFragmentById(R.id.table_list_fragment) == null) {
                val fragment = TableListFragment.newInstance()

                supportFragmentManager.beginTransaction()
                        .add(R.id.table_list_fragment, fragment)
                        .commit()
            }
        }

        lastTableIndex = intent.getIntExtra(EXTRA_TABLE, 0)

        if (findViewById<ViewGroup>(R.id.course_list_by_table_fragment) != null) {
            if (supportFragmentManager.findFragmentById(R.id.course_list_by_table_fragment) == null) {
                supportFragmentManager.beginTransaction()
                        .add(R.id.course_list_by_table_fragment, CourseListByTableFragment.newInstance(lastTableIndex))
                        .commit()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        if (findViewById<ViewGroup>(R.id.course_list_by_table_fragment) != null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.course_list_by_table_fragment, CourseListByTableFragment.newInstance(lastTableIndex))
                    .commit()

        }
    }

    override fun onBackPressed() {
        if (findViewById<ViewGroup>(R.id.table_list_fragment) != null) {
            val resultIntent = Intent()
            resultIntent.putExtra(TableListActivity.EXTRA_BACK_PRESSED, true)

            setResult(Activity.RESULT_OK, resultIntent)
        }

        super.onBackPressed()

    }

    override fun onTableSelected(table: Table, position: Int) {
        lastTableIndex = position

        if (findViewById<ViewGroup>(R.id.course_list_by_table_fragment) != null) {
            val courseListFragment = supportFragmentManager.findFragmentById(R.id.course_list_by_table_fragment) as? CourseListByTableFragment

            if (courseListFragment != null) {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.course_list_by_table_fragment, CourseListByTableFragment.newInstance(position))
                        .commit()
            }
        }
    }

    override fun onCourseSelected(course: Course, position: Int) {
        val intent = CourseDetailActivity.intent(this, course)
        startActivity(intent)
    }
}
