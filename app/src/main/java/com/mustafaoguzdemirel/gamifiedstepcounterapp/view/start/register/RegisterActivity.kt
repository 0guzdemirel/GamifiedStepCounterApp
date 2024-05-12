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
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.avatar.AvatarModel
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.UserModel
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.main.MainActivity
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.start.LoginActivity
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

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
        replaceFragment()
        setListeners()
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
            Dataholder.instance.currentUserModel = UserModel(
                id = "1",
                name = binding.nameET.text.toString(),
                email = binding.emailET.text.toString(),
                password = binding.passwordET.text.toString(),
                avatarId = selectedAvatarId,
                streakCount = 23,
                avgStepCount = 9232,
                streakRanking = 10,
                avgStepRanking = 23,
                todaysStepCount = 2348,
                coin = 100
            )
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