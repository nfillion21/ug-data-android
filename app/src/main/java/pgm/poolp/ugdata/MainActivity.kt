package pgm.poolp.ugdata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pgm.poolp.ugdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
}