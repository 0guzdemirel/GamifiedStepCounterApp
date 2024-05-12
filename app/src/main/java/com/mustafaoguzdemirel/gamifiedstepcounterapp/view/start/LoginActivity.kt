package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.start

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.ActivityLoginBinding
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.Dataholder
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.NavigationHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.UserModel
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.main.MainActivity
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.start.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.loginRL.setOnClickListener {
            finish()
            Dataholder.instance.currentUserModel = UserModel(
                id = "1",
                name = "Mustafa Demirel",
                email = binding.emailET.text.toString(),
                password = binding.passwordET.text.toString(),
                avatarId = 5,
                streakCount = 23,
                avgStepCount = 9232,
                streakRanking = 10,
                avgStepRanking = 23,
                todaysStepCount = 2348,
                coin = 100
            )
            NavigationHelper.instance?.navigateToActivity(
                context = this@LoginActivity,
                navigateActivity = MainActivity::class.java
            )

            //TODO: login api
        }

        binding.registerRL.setOnClickListener {
            finish()
            NavigationHelper.instance?.navigateToActivity(
                context = this@LoginActivity,
                navigateActivity = RegisterActivity::class.java
            )
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}