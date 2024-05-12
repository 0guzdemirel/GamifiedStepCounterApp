package com.mustafaoguzdemirel.gamifiedstepcounterapp.helper

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.PorterDuff
import android.graphics.Shader
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.mustafaoguzdemirel.gamifiedstepcounterapp.R
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.avatar.AvatarModel
import java.util.Calendar

object UIHelper {

    fun paintTextWithGradientColor(textView: TextView, color1: String?, color2: String?) {
        val paint = textView.paint
        val width = paint.measureText(textView.text.toString())
        val textShader: Shader = LinearGradient(
            0f, 0f, width, textView.textSize, intArrayOf(
                Color.parseColor(color1),
                Color.parseColor(color2)
            ), null, Shader.TileMode.CLAMP
        )
        textView.paint.setShader(textShader)
    }

    fun setButtonLoading(buttonL: RelativeLayout, buttonText: TextView, progressBar: ProgressBar) {
        buttonL.isEnabled = false
        buttonL.alpha = 0.5f
        buttonText.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    fun setButtonLoaded(buttonL: RelativeLayout, buttonText: TextView, progressBar: ProgressBar) {
        buttonL.isEnabled = true
        buttonL.alpha = 1f
        buttonText.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    fun setButtonLoading(icon: ImageView?, progressBar: ProgressBar?) {
        icon?.visibility = View.GONE
        progressBar?.visibility = View.VISIBLE
    }

    fun setButtonLoaded(icon: ImageView?, progressBar: ProgressBar?) {
        icon?.visibility = View.VISIBLE
        progressBar?.visibility = View.GONE
    }

    fun setButtonEnabled(buttonL: RelativeLayout) {
        buttonL.isEnabled = true
        buttonL.alpha = 1f
    }

    fun setButtonDisabled(buttonL: RelativeLayout) {
        buttonL.isEnabled = false
        buttonL.alpha = 0.5f
    }

    fun setImageTint(context: Context?, imageView: ImageView, colorId: Int) {
        imageView.setColorFilter(
            ContextCompat.getColor(context!!, colorId),
            PorterDuff.Mode.MULTIPLY
        )
    }

    fun pickAndSetDate(
        context: Context?,
        textView: TextView,
    ) {
        val c = Calendar.getInstance()
        val year = c[Calendar.YEAR]
        val month = c[Calendar.MONTH]
        val day = c[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(
            context!!,
            { view, year, monthOfYear, dayOfMonth ->
                val day: String
                val month: String
                day = if (dayOfMonth < 10) "0$dayOfMonth" else dayOfMonth.toString()
                month =
                    if (monthOfYear + 1 < 10) "0" + (monthOfYear + 1) else (monthOfYear + 1).toString()
                textView.text = "$day.$month.$year"
            }, year, month, day
        )
        datePickerDialog.datePicker.maxDate = Calendar.getInstance().timeInMillis
        datePickerDialog.show()
    }

    fun getAvatar(index: Int?): Int {
        val avatarList: MutableList<AvatarModel> = mutableListOf(
            AvatarModel(id = 0, drawableId = R.drawable.avatar_1, isSelected = false),
            AvatarModel(id = 1, drawableId = R.drawable.avatar_2, isSelected = false),
            AvatarModel(id = 2, drawableId = R.drawable.avatar_3, isSelected = false),
            AvatarModel(id = 3, drawableId = R.drawable.avatar_4, isSelected = false),
            AvatarModel(id = 4, drawableId = R.drawable.avatar_5, isSelected = false),
            AvatarModel(id = 5, drawableId = R.drawable.avatar_6, isSelected = false),
            AvatarModel(id = 6, drawableId = R.drawable.avatar_7, isSelected = false),
            AvatarModel(id = 7, drawableId = R.drawable.avatar_8, isSelected = false),
            AvatarModel(id = 8, drawableId = R.drawable.avatar_9, isSelected = false),
            AvatarModel(id = 9, drawableId = R.drawable.avatar_10, isSelected = false),
            AvatarModel(id = 10, drawableId = R.drawable.avatar_11, isSelected = false),
            AvatarModel(id = 11, drawableId = R.drawable.avatar_12, isSelected = false),
            AvatarModel(id = 12, drawableId = R.drawable.avatar_13, isSelected = false),
            AvatarModel(id = 13, drawableId = R.drawable.avatar_14, isSelected = false),
            AvatarModel(id = 14, drawableId = R.drawable.avatar_15, isSelected = false),
            AvatarModel(id = 15, drawableId = R.drawable.avatar_16, isSelected = false),
            AvatarModel(id = 16, drawableId = R.drawable.avatar_17, isSelected = false),
            AvatarModel(id = 17, drawableId = R.drawable.avatar_18, isSelected = false),
        )
        if (index != null)
            return avatarList[index].drawableId
        else
            return -1
    }
}
