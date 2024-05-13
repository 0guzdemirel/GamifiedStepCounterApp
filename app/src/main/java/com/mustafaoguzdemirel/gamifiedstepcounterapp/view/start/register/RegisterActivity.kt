package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.start.register

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.hukukkarar.app.common.helper.FragmentHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.ActivityRegisterBinding
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.Dataholder
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.NavigationHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.UIHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.avatar.AvatarModel
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.UserModel
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.UserResponse
import com.mustafaoguzdemirel.gamifiedstepcounterapp.presenter.login.LoginPresenter
import com.mustafaoguzdemirel.gamifiedstepcounterapp.presenter.login.LoginView
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.main.MainActivity
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.start.LoginActivity
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import es.dmoral.toasty.Toasty

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var loginPresenter: LoginPresenter

    private var selectedAvatarId = -1

    private val avatarFragment = AvatarFragment(
        avatarCallback = object : AvatarCallback {
            override fun onAvatarSelected(avatarModel: AvatarModel) {
                selectedAvatarId = avatarModel.id
                binding.profilePhotoIV.setImageResource(avatarModel.drawableId)
                binding.slidingUpPanelLayout.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPresenter()
        replaceFragment()
        setListeners()
    }

    private fun initPresenter() {
        loginPresenter = LoginPresenter()
    }

    private fun setListeners() {
        binding.profilePhotoContainerRL.setOnClickListener {
            binding.slidingUpPanelLayout.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
        }

        binding.loginRL.setOnClickListener {
            finish()
            NavigationHelper.instance?.navigateToActivity(
                context = this@RegisterActivity,
                navigateActivity = LoginActivity::class.java
            )
        }

        binding.registerRL.setOnClickListener {
            finish()
            NavigationHelper.instance?.navigateToActivity(
                context = this@RegisterActivity,
                navigateActivity = MainActivity::class.java
            )
            //TODO api call
        }

        binding.slidingUpPanelLayout.setFadeOnClickListener {
            binding.slidingUpPanelLayout.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        }
    }

    private fun login() {
        UIHelper.setButtonLoading(
            buttonL = binding.registerRL,
            buttonText = binding.registerBtnText,
            progressBar = binding.registerBtnPB
        )

        loginPresenter.register(
            email = binding.emailET.text.toString(),
            password = binding.passwordET.text.toString(),
            name = binding.nameET.text.toString(),
            avataarId = "" + selectedAvatarId,
            loginView = object : LoginView {
                override fun onRegisterSuccessful(userResponse: UserResponse?) {
                    UIHelper.setButtonLoaded(
                        buttonL = binding.registerRL,
                        buttonText = binding.registerBtnText,
                        progressBar = binding.registerBtnPB
                    )
                    Dataholder.instance.currentUserModel = userResponse?.userData?.userModel
                    NavigationHelper.instance?.navigateToActivity(
                        context = this@RegisterActivity,
                        navigateActivity = MainActivity::class.java
                    )
                    finish()
                }

                override fun onError() {
                    Toasty.error(
                        this@RegisterActivity,
                        "Bir hata oluştu",
                        Toasty.LENGTH_SHORT,
                        false
                    ).show()

                    UIHelper.setButtonLoaded(
                        buttonL = binding.registerRL,
                        buttonText = binding.registerBtnText,
                        progressBar = binding.registerBtnPB
                    )
                }
            }
        )
    }

    private fun replaceFragment() {
        FragmentHelper.instance?.replaceFragment(
            fragmentManager = supportFragmentManager,
            fragment = avatarFragment,
            containerId = binding.fragmentContainerFL.id
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