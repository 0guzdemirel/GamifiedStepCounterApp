package com.mustafaoguzdemirel.gamifiedstepcounterapp.presenter.login

import com.mustafaoguzdemirel.gamifiedstepcounterapp.api.ApiInterface
import com.mustafaoguzdemirel.gamifiedstepcounterapp.api.AppApiClient
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.user.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter() {
    private val apiInterface = AppApiClient.clientWithOutToken.create(
        ApiInterface::class.java
    )

    fun login(
        email: String?,
        password: String?,
        loginView: LoginView
    ) {
        apiInterface.login(
            email,
            password
        )?.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(
                call: Call<UserResponse?>,
                response: Response<UserResponse?>
            ) {
                try {
                    if (response.isSuccessful && response.body()?.meta?.code == 200) {
                        loginView.onLoginSuccessful(response.body())
                    } else {
                        loginView.onError()
                    }
                } catch (ex: Exception) {
                    loginView.onError()
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                loginView.onError()
            }
        })
    }

    fun register(
        email: String?,
        password: String?,
        name: String?,
        avataarId: String?,
        loginView: LoginView
    ) {
        apiInterface.register(
            email,
            password,
            name,
            avataarId
        )?.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(
                call: Call<UserResponse?>,
                response: Response<UserResponse?>
            ) {
                try {
                    if (response.isSuccessful && response.body()?.meta?.code == 200) {
                        loginView.onRegisterSuccessful(response.body())
                    } else {
                        loginView.onError()
                    }
                } catch (ex: Exception) {
                    loginView.onError()
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                loginView.onError()
            }
        })
    }

}