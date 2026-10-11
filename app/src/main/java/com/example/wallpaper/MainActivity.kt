package com.example.wallpaper

import android.app.WallpaperManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    private lateinit var wallpaperManager: WallpaperManager
    private val handler = Handler(Looper.getMainLooper())
    private var isChanging = false

    // wallpaper1.jpg, wallpaper2.jpg, wallpaper3.jpg, wallpaper4.jpg in res/drawable
    private val wallpaperImages = listOf(
        R.drawable.wallpaper1,
        R.drawable.wallpaper2,
        R.drawable.wallpaper3,
        R.drawable.wallpaper4
    )

    private val wallpaperRunnable = object : Runnable {
        override fun run() {
            changeWallpaperRandomly()
            handler.postDelayed(this, 10000) // 30 seconds
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wallpaperManager = WallpaperManager.getInstance(applicationContext)

        setContent {
            MaterialTheme {
                WallpaperChangerScreen(
                    onStartClick = { startWallpaperChanging() },
                    onStopClick = { stopWallpaperChanging() }
                )
            }
        }
    }

    private fun startWallpaperChanging() {
        if (!isChanging) {
            isChanging = true
            handler.post(wallpaperRunnable)
            Toast.makeText(
                this,
                "Wallpaper auto-change started",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun stopWallpaperChanging() {
        if (isChanging) {
            isChanging = false
            handler.removeCallbacks(wallpaperRunnable)
            Toast.makeText(
                this,
                "Wallpaper auto-change stopped",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun changeWallpaperRandomly() {
        try {
            val randomImageRes = wallpaperImages.random()
            // Decode the drawable into a Bitmap and set it directly.
            // More reliable than setResource() on many emulator launchers.
            val bitmap = BitmapFactory.decodeResource(resources, randomImageRes)
            if (bitmap != null) {
                wallpaperManager.setBitmap(bitmap)
            } else {
                Toast.makeText(
                    this,
                    "Could not decode wallpaper image",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Failed to set wallpaper: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroy() {
        handler.removeCallbacks(wallpaperRunnable)
        super.onDestroy()
    }
}

@Composable
fun WallpaperChangerScreen(
    onStartClick: () -> Unit,
    onStopClick: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Random Wallpaper Changer",
                fontSize = 24.sp,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = onStartClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Start Auto Wallpaper Change")
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = onStopClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Stop Auto Wallpaper Change")
            }
        }
    }
}
