package com.mustafaoguzdemirel.gamifiedstepcounterapp.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.mustafaoguzdemirel.gamifiedstepcounterapp.R

class NavigationHelper private constructor() {
    fun navigateTo(context: Context, activity: Class<*>?) {
        val intent = Intent(context, activity)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun navigateToWithoutClearingTop(context: Context, activity: Class<*>?) {
        val intent = Intent(context, activity)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun navigateToActivity(context: Context?, navigateActivity: Class<*>?) {
        if (context != null) {
            navigateToWithoutClearingTop(context, navigateActivity)
            (context as Activity).overridePendingTransition(
                R.anim.slide_from_right_renamed,
                R.anim.slide_to_left_renamed
            )
        }
    }

    companion object {
        var instance: NavigationHelper? = null
            get() {
                if (field == null) field = NavigationHelper()
                return field
            }
            private set
    }

}
