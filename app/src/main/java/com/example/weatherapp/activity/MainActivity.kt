package com.example.weatherapp.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.CurrentResponeApi
import com.example.weatherapp.viewmodel.WeatherViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels()
    private val calendar by lazy { Calendar.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate and set the content view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Set binding root as content view

        // Enable edge-to-edge display
        enableEdgeToEdge()

        // Handle window insets for padding
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set transparent status bar
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
        }

        binding.apply {
            val lat = 14.0583
            val lon = 108.2772
            val name = "Vietnam"

            // Set city name on the UI
            cityTxt.text = name
            progressBar.visibility = View.VISIBLE

            // Fetch current weather data
            weatherViewModel.loadCurrentWeather(lat, lon, "metric")
                .enqueue(object : Callback<CurrentResponeApi> {
                    override fun onResponse(call: Call<CurrentResponeApi?>, response: Response<CurrentResponeApi?>) {
                        if (response.isSuccessful) {
                            val data = response.body()
                            progressBar.visibility = View.GONE
                            detailLayout.visibility = View.VISIBLE
                            data?.let {
                                // Set weather information to UI
                                statusTxt.text = it.weather?.get(0)?.main ?: "-"
                                windTxt.text = "${Math.round(it.wind?.speed ?: 0.0)} Km"
                                humidityTxt.text = it.main?.humidity?.toString() + "%"
                                currentTempTxt.text = "${Math.round(it.main?.temp ?: 0.0)}°"
                                maxTempTxt.text = "${Math.round(it.main?.tempMax ?: 0.0)}°"
                                minTempTxt.text = "${Math.round(it.main?.tempMin ?: 0.0)}°"

                                // Set background based on weather icon
                                val drawable = if (isNightNow()) R.drawable.night_bg
                                else {
                                    setDynamicallyWallpaper(it.weather?.get(0)?.icon ?: "-")
                                }
                                bgImage.setImageResource(drawable)
                            }
                        }
                    }

                    override fun onFailure(call: Call<CurrentResponeApi?>, t: Throwable) {
                        Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }

    private fun isNightNow(): Boolean {
        return calendar.get(Calendar.HOUR_OF_DAY) >= 18
    }

    private fun setDynamicallyWallpaper(icon: String): Int {
        return when (icon.dropLast(1)) {
            "01" -> R.drawable.snow_bg
            "02", "03", "04" -> R.drawable.cloudy_bg
            "09", "10", "11" -> R.drawable.rainy_bg
            "13" -> R.drawable.snow_bg
            "50" -> R.drawable.haze_bg
            else -> 0
        }
    }
}
