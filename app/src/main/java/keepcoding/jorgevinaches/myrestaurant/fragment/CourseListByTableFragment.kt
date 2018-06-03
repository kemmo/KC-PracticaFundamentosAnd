package keepcoding.jorgevinaches.myrestaurant.fragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.*

import keepcoding.jorgevinaches.myrestaurant.R
import keepcoding.jorgevinaches.myrestaurant.activity.MainCoursesListActivity
import keepcoding.jorgevinaches.myrestaurant.model.Tables
import kotlinx.android.synthetic.main.fragment_course_list_by_table.*

class CourseListByTableFragment : Fragment() {

    companion object {
        val ARG_TABLE = "ARG_TABLE"

        fun newInstance(tableIndex: Int): CourseListByTableFragment {
            val arguments = Bundle()
            arguments.putInt(ARG_TABLE, tableIndex)
            val fragment = CourseListByTableFragment()
            fragment.arguments = arguments

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val position = arguments?.getInt(ARG_TABLE, 0)

        childFragmentManager.beginTransaction()
                .add(R.id.child_course_list, CourseListFragment.newInstance(Tables[position!!].courses?.toTypedArray()))
                .commit()

        return inflater.inflate(R.layout.fragment_course_list_by_table, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_course_button.setOnClickListener {
            val position = arguments?.getInt(ARG_TABLE, 0)

            val intent = MainCoursesListActivity.intent(activity!!, Tables[position!!])
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.course_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_bill -> {
                val position = arguments?.getInt(ARG_TABLE, 0)
                var sum = 0f
                Tables[position!!].courses?.forEach {
                    sum += it.price
                }

                AlertDialog.Builder(activity!!)
                        .setTitle("Cuenta")
                        .setMessage("Suma de los platos es $sum")
                        .show()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
