package jth.kr.co.tabling.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TablingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}