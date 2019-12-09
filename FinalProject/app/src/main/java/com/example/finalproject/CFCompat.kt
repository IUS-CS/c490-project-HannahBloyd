package com.example.finalproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class CFCompat : AppCompatActivity(), ConnectFour.Callbacks {
    override fun endConnectFour() {
        val intent = CFEnd.newIntent(this@CFCompat)
        startActivityForResult(intent, GAME2_CODE)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            val fragment = ConnectFour.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    companion object {
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, CFCompat::class.java).apply {
            }
        }
    }
}