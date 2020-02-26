package com.kyawt.dotahero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kyawt.dotahero.ui.HeroFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            val heroFragment = HeroFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.screen_container, heroFragment).commit()
        }
    }
}
