package com.example.noteappliccation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp



/*  This is an important class while using hilt.
    This class extends the Application class which is the top level class when we added
    @HiltAndroidApp annotation to the Application class, the hilt can access or supply
    dependency any where in the application. After creating this class we should regis-
    ter this class in the android manifest file.
 */
@HiltAndroidApp
class NoteApplication: Application() {
}