package com.myd.sccasestudy

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dataSource = DataSource()

        dataSource.fetch("10", object : FetchCompletionHandler {
            override fun invoke(p1: FetchResponse?, p2: FetchError?) {
                Toast.makeText(this@MainActivity, p1?.people?.first()?.fullName, Toast.LENGTH_SHORT).show()
            }

        })
    }
}