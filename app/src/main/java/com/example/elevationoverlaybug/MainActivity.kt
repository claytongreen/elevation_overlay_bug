package com.example.elevationoverlaybug

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import com.google.android.material.shape.MaterialShapeDrawable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.white_87).setMaterialShapeDrawable()
        findViewById<TextView>(R.id.white_87_argb).setMaterialShapeDrawable()

        findViewById<Button>(R.id.button).setOnClickListener {
            val currentNightMode =
                resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            val mode = when (currentNightMode) {
                Configuration.UI_MODE_NIGHT_YES -> AppCompatDelegate.MODE_NIGHT_NO
                Configuration.UI_MODE_NIGHT_NO -> AppCompatDelegate.MODE_NIGHT_YES
                else -> AppCompatDelegate.MODE_NIGHT_YES
            }

            AppCompatDelegate.setDefaultNightMode(mode)
            delegate.localNightMode = mode
        }
    }

    private fun View.setMaterialShapeDrawable() {
        val elevation = resources.getDimension(R.dimen.elevation)
        val shape = MaterialShapeDrawable.createWithElevationOverlay(
            context,
            elevation
        )
        ViewCompat.setBackground(this, shape)
        ViewCompat.setElevation(this, elevation)
    }
}