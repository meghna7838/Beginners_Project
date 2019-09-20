package mykotlin.meghna.com.listview

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.item_movie.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

       val movie:Movie=intent.getSerializableExtra("movie") as Movie

        imageView.setImageResource(movie.image)
        name.setText(movie.name+"("+movie.year+")")
        actor.setText(movie.actor)

    }
}
