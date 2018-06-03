package keepcoding.jorgevinaches.myrestaurant.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.*
import keepcoding.jorgevinaches.myrestaurant.R
import keepcoding.jorgevinaches.myrestaurant.adapter.CourseRecyclerViewAdapter
import keepcoding.jorgevinaches.myrestaurant.adapter.RecyclerViewClickListener
import keepcoding.jorgevinaches.myrestaurant.model.Course
import kotlinx.android.synthetic.main.fragment_course_list.*
import java.io.Serializable

interface OnCourseSelectedListener {
    fun onCourseSelected(course: Course, position: Int)
}

class CourseListFragment : Fragment(), RecyclerViewClickListener {

    companion object {
        val ARG_COURSE_ARRAY = "ARG_COURSE_ARRAY"

        fun newInstance(courseArray: Serializable?): CourseListFragment {
            val arguments = Bundle()
            arguments.putSerializable(CourseListFragment.ARG_COURSE_ARRAY, courseArray)
            val fragment = CourseListFragment()
            fragment.arguments = arguments

            return fragment
        }
    }

    private var onCourseSelectedListener: OnCourseSelectedListener? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_course_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var courseList: Array<Course>? = arguments?.getSerializable(ARG_COURSE_ARRAY) as? Array<Course>
        if (courseList == null) {
            courseList = arrayOf()
        }
        course_list.layoutManager = GridLayoutManager(activity, 1)

        val adapter = CourseRecyclerViewAdapter(courseList)
        adapter.itemListener = this

        course_list.adapter = adapter
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context as Activity)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    private fun commonAttach(activity: Activity?) {
        if (activity is OnCourseSelectedListener) {
            onCourseSelectedListener = activity
        }
        else {
            onCourseSelectedListener = null
        }
    }

    override fun recyclerViewListClicked(v: View, position: Int) {
        var courseList: Array<Course>? = arguments?.getSerializable(ARG_COURSE_ARRAY) as? Array<Course>
        if (courseList == null) {
            courseList = arrayOf()
        }
        onCourseSelectedListener?.onCourseSelected(courseList[position], position)
    }
}
