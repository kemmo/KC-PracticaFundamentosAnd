package keepcoding.jorgevinaches.myrestaurant.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import keepcoding.jorgevinaches.myrestaurant.R
import keepcoding.jorgevinaches.myrestaurant.fragment.CourseListByTableFragment
import keepcoding.jorgevinaches.myrestaurant.fragment.OnCourseSelectedListener
import keepcoding.jorgevinaches.myrestaurant.fragment.OnTableSelectedListener
import keepcoding.jorgevinaches.myrestaurant.fragment.TableListFragment
import keepcoding.jorgevinaches.myrestaurant.model.Course
import keepcoding.jorgevinaches.myrestaurant.model.Table

class TableListActivity : AppCompatActivity(), OnTableSelectedListener, OnCourseSelectedListener {

    companion object {
        val EXTRA_BACK_PRESSED = "EXTRA_BACK_PRESSED"
        val REQUEST_CODE = 1
    }

    var lastTableIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_list)

        if (findViewById<ViewGroup>(R.id.table_list_fragment) != null) {
            if (supportFragmentManager.findFragmentById(R.id.table_list_fragment) == null) {
                val fragment = TableListFragment.newInstance()

                supportFragmentManager.beginTransaction()
                        .add(R.id.table_list_fragment, fragment)
                        .commit()
            }
        }

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
            val courseListFragment = supportFragmentManager.findFragmentById(R.id.course_list_by_table_fragment) as? CourseListByTableFragment

            if (courseListFragment != null) {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.course_list_by_table_fragment, CourseListByTableFragment.newInstance(lastTableIndex))
                        .commit()
            }
        }
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
        else {
            val intent = CourseListActivity.intent(this, position)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onCourseSelected(course: Course, position: Int) {
        val intent = CourseDetailActivity.intent(this, course)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if ((data != null) && (data!!.extras.getBoolean(EXTRA_BACK_PRESSED))) {
                    finish()
                }
            }
        }
    }
}
