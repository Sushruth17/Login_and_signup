package com.example.login_and_signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.login_and_signup.model.MockData
import com.example.login_and_signup.utils.StringUtils
import com.google.android.material.navigation.NavigationView
import okhttp3.ResponseBody



class Home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.i("Learning","------ Home activity A on create --------- ")
        var data = getData()
        setContentView(R.layout.activity_home)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

    val buttonstd = findViewById<Button>(R.id.btn_stdDetails)
    buttonstd.setOnClickListener{
        Log.i("api","------ Button clicked --------- ")
        val intent = Intent(this, StudentDetails::class.java)
        intent.putExtra(StringUtils.STUDENT_INFO_DATA,getData())
        startActivity(intent)
        /*Log.i("api","------ After start activity Button clicked --------- ")
        val apiKindaStuff = APIKindaStuff()
            .addRetroFit()
            .enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                    Log.i("api","---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                }

                override fun onResponse(
                    call: retrofit2.Call<ResponseBody>,
                    response: retrofit2.Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val msg = response.body()?.string()
                        Log.i("api","---TTTT :: GET msg from server :: " + msg)
                        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            })

        Log.i("here","------this is apikindastuff--------" + apiKindaStuff)*/


    }

        val buttonmarks = findViewById<Button>(R.id.btn_stdMarks)
        buttonmarks.setOnClickListener{
            val intent = Intent(this, StudentMarks::class.java)
//            intent.putExtra(StringUtils.STUDENT_INFO_DATA,getData())
            startActivity(intent)
        }
}

    override fun onPause() {
        super.onPause()
        Log.i("Learning","------ Home activity A on pause --------- ")
    }


    override fun onStop() {
        super.onStop()
        Log.i("Learning","------ Home activity A on stop --------- ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Learning","------ Home activity A on destroy --------- ")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Learning","------ Home activity A on start --------- ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Learning","------ Home activity A on restart --------- ")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Learning","------ Home activity A on resume --------- ")
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_messages -> {
                Toast.makeText(this, "Messages clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_friends -> {
                Toast.makeText(this, "Friends clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_update -> {
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    fun getData(): String {
//        val gson = Gson()
        val json = getDataFromRepo()
//        val studentInfoData = gson.fromJson(json, StudentInfoModel::class.java)
    return json
    }
    fun getDataFromRepo(): String {
        return MockData.data
    }



}