package keepcoding.jorgevinaches.myrestaurant.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import keepcoding.jorgevinaches.myrestaurant.R
import keepcoding.jorgevinaches.myrestaurant.model.Allergen
import keepcoding.jorgevinaches.myrestaurant.model.Course

interface RecyclerViewClickListener {
    fun recyclerViewListClicked(v: View, position: Int)
}

class CourseRecyclerViewAdapter(private val courses: Array<Course>): RecyclerView.Adapter<CourseRecyclerViewAdapter.CourseViewHolder>() {
    var itemListener: RecyclerViewClickListener? = null

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_course, parent, false)

        return CourseViewHolder(view)
    }

    override fun getItemCount() = courses.count()

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bindCourse(courses[position])
    }

    inner class CourseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val courseName = itemView.findViewById<TextView?>(R.id.course_name)
        val courseImage = itemView.findViewById<ImageView?>(R.id.course_image)
        val coursePrice = itemView.findViewById<TextView?>(R.id.course_price)
        val courseIngredients = itemView.findViewById<TextView?>(R.id.course_ingredients)
        val courseVariants = itemView.findViewById<TextView?>(R.id.course_variants)
        val allergenShellfish = itemView.findViewById<ImageView?>(R.id.allergen_shellfish)
        val allergenEgg = itemView.findViewById<ImageView?>(R.id.allergen_egg)
        val allergenGluten = itemView.findViewById<ImageView?>(R.id.allergen_gluten)
        val allergenLactose = itemView.findViewById<ImageView?>(R.id.allergen_lactose)
        val allergenFish = itemView.findViewById<ImageView?>(R.id.allergen_fish)
        val allergenSoy = itemView.findViewById<ImageView?>(R.id.allergen_soy)
        val allergenHusk = itemView.findViewById<ImageView?>(R.id.allergen_husks)
        val context = itemView.context

        fun bindCourse(course: Course) {
            courseImage?.setImageResource(course.imageRes)
            courseName?.text = course.name

            var ingredients = "INGREDIENTES: \r\n"
            course.ingredients.forEach {
                ingredients = "$ingredients$it, "
            }

            courseIngredients?.text = ingredients
            coursePrice?.text = "PRECIO: ${course.price}"
            courseVariants?.text = course.variants

            course.allergens?.forEach {
                when(it) {
                    Allergen.MARISCO -> allergenShellfish?.visibility = View.VISIBLE
                    Allergen.HUEVO -> allergenEgg?.visibility = View.VISIBLE
                    Allergen.GLUTEN -> allergenGluten?.visibility = View.VISIBLE
                    Allergen.LACTOSA -> allergenLactose?.visibility = View.VISIBLE
                    Allergen.PESCADO -> allergenFish?.visibility = View.VISIBLE
                    Allergen.SOJA -> allergenSoy?.visibility = View.VISIBLE
                    Allergen.CASCARAS -> allergenHusk?.visibility = View.VISIBLE
                }
            }

            itemView.setOnClickListener {
                itemListener?.recyclerViewListClicked(it, this.layoutPosition)
            }
        }
    }
}