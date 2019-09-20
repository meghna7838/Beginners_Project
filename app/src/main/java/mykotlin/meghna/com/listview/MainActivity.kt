package mykotlin.meghna.com.listview
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_movie.view.*
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

   // val ListView:ListView?=null
   val movies=arrayOf("IronMan","Thor","Captain America","Black Panther","End game")
    val yearOfRelease=arrayOf("2008","2010","2011","2018","2019")
    val actors=arrayOf("ROJ","chris hemsworth","chirs Evans","chadwick","ROJ")
    val image=arrayOf(
        R.drawable.ironman,
        R.drawable.thor,
        R.drawable.cap,
        R.drawable.blackpanther,
        R.drawable.avenger
    )
    val list:ArrayList<Movie> =arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..99) {
            val random = Random().nextInt(5)
            list.add(
                Movie(
                    movies[random],
                    yearOfRelease[random],
                    actors[random],
                    image[random]
                )
            )
        }
        val adapter =MovieAdapter(list)
        adapter.onItemClickListener = object:MovieonItemClickListener{
            override fun onItemClick(movie:Movie)
            {

                val i= Intent(this@MainActivity,Main2Activity::class.java)
                i.putExtra("movie",movie)

                Toast.makeText(this@MainActivity,"${movie.name}",Toast.LENGTH_LONG).show()//activity open karni hae
                startActivity(i)
            }
        }
        lv.adapter = adapter//lv is id of list view
      //  lv=findViewById(R.id.lv)
              /* if we add basic tareeke se  list.addAll(
                    listOf(
                        Movie("Iron Man","2008","tony",1),
                        Movie("Thor","2010","tony",1),
                        Movie("Captain America","2011","tony",1),
                        Movie("Black panther","2018","tony",1),
                        Movie("End game","2019","tony",1)

                        ))//scrollable hoga yeah sab*/
       // lv.adapter= ArrayAdapter<Movie>(this,android.R.layout.simple_expandable_list_item_1,list)//simple_e_l_i_1is the inbuilt view already present in android//yaha list_item2 bhi aarha tha wt is that

    }
}

 data class Movie(
                 val name:String,//data likhane se bahut sare methods overriden defined milte hae sath me with special properties jaise toString() will give data instead of reference
                 val year:String,
                 val actor:String,
                 val image:Int
 ) : Serializable

class MovieAdapter(val movies:ArrayList<Movie>):BaseAdapter(){
    var onItemClickListener: MovieonItemClickListener? = null
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    val inflator=LayoutInflater.from(parent.context)//why parent.context?

       /* if(convertView==null)
        {
            view=inflator.inflate(R.layout.item_movie,parent,false)
        }
        else
        {
            view=convertView
        }*/
      val view=convertView?:inflator.inflate(R.layout.item_movie,parent,false)//if else alternative//parent is used as hame listview ki width jina chahie
        view.name.text=movies[position].name+"("+movies[position].year+")"
        view.actor.text=movies[position].actor
        view.imageView.setImageResource(movies[position].image)
        view.setOnClickListener {
            onItemClickListener?.onItemClick(movies[position])
        }
    return view
    }

    override fun getItem(position: Int):Movie=movies[position]//always same for all classes

    override fun getItemId(position: Int): Long =position.toLong()//always same for all classes

    override fun getCount(): Int =movies.size//always same for all classes
}
interface MovieonItemClickListener{
    fun onItemClick(movie:Movie)
}