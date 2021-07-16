package com.example.myfilms.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.myfilms.R
import com.example.myfilms.databinding.MainActivityBinding
import com.example.myfilms.framework.ui.History.HistoryFragment
import com.example.myfilms.framework.ui.map.MapsFragment
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.IOException

//С сервисом нормально не успела.

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var binding : MainActivityBinding

    private val permissionResult = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            result ->
        if (result) {
            getLocation()
        } else {
            Toast.makeText(this, getString(R.string.need_permission), Toast.LENGTH_SHORT).show()
        }
    }

    //получает местоположение
    private val onLocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            getAddressAsync(location) //будем брать адрес/ Локейшн - объект, откуда мы можем получить координаты
        }
        //когда поменялся статус (напр, сила сигнала)
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        //когда вкл, когда выкл
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_screen_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.look_history -> {
                supportFragmentManager.apply {
                    beginTransaction()
                        .add(R.id.container, HistoryFragment.newInstance())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                true
//            }R.id.look_call_me -> {
//                supportFragmentManager.apply {
//                    beginTransaction()
//                        .add(R.id.container, ContactsFragment.newInstance())
//                        .addToBackStack("")
//                        .commitAllowingStateLoss()
//                }
//                true
            }R.id.where_am_i -> {
              supportFragmentManager.apply {
                  checkPermission()
              }
                true
            }R.id.map_open -> {
                supportFragmentManager.apply {
                    beginTransaction()
                        .add(R.id.container, MapsFragment.newInstance())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun checkPermission() {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            getLocation()
        } else {
            permissionResult.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val locationManager = //он отвечает за определение месторасположения
            this.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            val provider = locationManager.getProvider(LocationManager.GPS_PROVIDER)
            provider?.let { //проверяем, досупен ли провайдер. Вдруг устройство его не имеет!
                // Будем получать геоположение через каждые 60 секунд и 10 метров, по факту подписка
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    5000L,
                    10f,
                    onLocationListener //он будет получать местоположение, что мы определили
                )
            }
        } else {
            //разово нельзя положение запросить. Можно последнее известное, LastKnown. Оно может быть и старым
            val location =
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location == null) {
                Toast.makeText(this, getString(R.string.looks_like_no_geo), Toast.LENGTH_SHORT
                ).show()
            } else {
                getAddressAsync(location)
            }
        }
    }

    private fun getAddressAsync(location: Location) {
        //геокодер - стандартный класс, не нужны библиотеки
        val geoCoder = Geocoder(this)
        //выход в интернет, делаем в фоновом потоке
        launch(Dispatchers.IO) {
            try {
                //может вернуть несколько адресов, ставим макс резалт
                val addresses = geoCoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
                )
                //показываем этот адрес в диалоге
                container.post {
                    //можно взять не адрес, а, например, имя страны
                    showAddressDialog(addresses.first().getAddressLine(0), location)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun showAddressDialog(address: String, location: Location) {
        let {
            AlertDialog.Builder(it)
                .setTitle(getString(R.string.your_adres))
                .setMessage(address)
                .setPositiveButton("Ок") { _, _ ->

                }
                .setNegativeButton(getString(R.string.close)) { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
    }

}

