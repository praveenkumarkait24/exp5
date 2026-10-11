# Experiment 5: Random Wallpaper Changer Application

## Aim
To design and develop an Android application that sets an image as the device wallpaper, and on clicking a button, automatically changes the wallpaper to a random image from a predefined set every 30 seconds.

## Objectives
- To understand and use the Android WallpaperManager API.
- To dynamically set an image resource as the device wallpaper.
- To implement a recurring background task using Handler and postDelayed().
- To design a simple user interface with Start/Stop buttons using Jetpack Compose.
- To declare and use the SET_WALLPAPER permission.
- To stop a periodic task cleanly and release resources in onDestroy().

## Procedure / Algorithm
1. Create a new Android project using Kotlin and Jetpack Compose.
2. Declare the SET_WALLPAPER permission in AndroidManifest.xml.
3. Add a set of sample images (wallpaper1, wallpaper2, wallpaper3, wallpaper4) to the res/drawable folder.
4. Design the user interface in MainActivity.kt with a Start Auto Wallpaper Change button and a Stop Auto Wallpaper Change button.
5. Obtain an instance of WallpaperManager using WallpaperManager.getInstance(context).
6. On clicking the Start button, use a Handler with postDelayed() to repeat a task every 30,000 milliseconds (30 seconds).
7. Inside the repeating task, randomly select one image from the drawable list, decode it into a Bitmap, and set it as the wallpaper using wallpaperManager.setBitmap().
8. On clicking the Stop button, remove the pending callbacks so the wallpaper stops changing.
9. Handle exceptions that may occur while decoding or setting the wallpaper.
10. Remove all pending callbacks in onDestroy() to avoid memory leaks.
11. Run the application on an emulator/device, click Start, press Home to view the home screen, and verify that the wallpaper changes randomly every 30 seconds; click Stop to verify it stops.

## Source Code Files
- `app/src/main/AndroidManifest.xml`
- `app/src/main/java/com/example/wallpaper/MainActivity.kt`

## Result
Thus, an Android application to set an image as wallpaper, and to automatically change the wallpaper to a random image every 30 seconds on clicking a button, was successfully designed and implemented.
