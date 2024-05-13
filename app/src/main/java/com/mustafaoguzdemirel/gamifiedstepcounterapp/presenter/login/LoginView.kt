package com.mustafaoguzdemirel.gamifiedstepcounterapp.presenter.login

import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.UserResponse

interface LoginView {
    fun onLoginSuccessful(userResponse: UserResponse?) {}
    fun onRegisterSuccessful(userResponse: UserResponse?) {}
    fun onError() {}
}
