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
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.UIHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.UserResponse
import com.mustafaoguzdemirel.gamifiedstepcounterapp.presenter.login.LoginPresenter
import com.mustafaoguzdemirel.gamifiedstepcounterapp.presenter.login.LoginView
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.main.MainActivity
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.start.register.RegisterActivity
import es.dmoral.toasty.Toasty

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPresenter()
        setListeners()
    }

    private fun initPresenter() {
        loginPresenter = LoginPresenter()
    }

    private fun setListeners() {
        binding.loginRL.setOnClickListener {
            login()
        }

        binding.registerRL.setOnClickListener {
            finish()
            NavigationHelper.instance?.navigateToActivity(
                context = this@LoginActivity,
                navigateActivity = RegisterActivity::class.java
            )
        }
    }

    private fun login() {
        UIHelper.setButtonLoading(
            buttonL = binding.loginRL,
            buttonText = binding.loginBtnText,
            progressBar = binding.loginBtnPB
        )

        loginPresenter.login(
            email = binding.emailET.text.toString(),
            password = binding.passwordET.text.toString(),
            loginView = object : LoginView {
                override fun onLoginSuccessful(userResponse: UserResponse?) {
                    UIHelper.setButtonLoaded(
                        buttonL = binding.loginRL,
                        buttonText = binding.loginBtnText,
                        progressBar = binding.loginBtnPB
                    )
                    Dataholder.instance.currentUserModel = userResponse?.userData?.userModel
                    NavigationHelper.instance?.navigateToActivity(
                        context = this@LoginActivity,
                        navigateActivity = MainActivity::class.java
                    )
                    finish()
                }

                override fun onError() {
                    Toasty.error(
                        this@LoginActivity,
                        "Bu bilgilere ait bir kayıt bulunamadı.",
                        Toasty.LENGTH_SHORT,
                        false
                    ).show()

                    UIHelper.setButtonLoaded(
                        buttonL = binding.loginRL,
                        buttonText = binding.loginBtnText,
                        progressBar = binding.loginBtnPB
                    )
                }
            }
        )
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