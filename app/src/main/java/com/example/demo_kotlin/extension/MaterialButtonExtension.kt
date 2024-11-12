package com.example.demo_kotlin.extension

import android.graphics.drawable.Drawable
import android.view.Gravity
import androidx.annotation.ColorInt
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicatorSpec
import com.google.android.material.progressindicator.IndeterminateDrawable

/**
 * Shows an indeterminate progress bar in place of the icon on the MaterialButton.
 * By default, the tint of the progress bar is the same as the iconTint.
 *
 * @param tintColor (@ColorInt Int) Sets the custom progress bar tint color.
 */
fun MaterialButton.showProgress(@ColorInt tintColor: Int = this.iconTint.defaultColor) {
    val spec = CircularProgressIndicatorSpec(
        context, null, 0,
    )
    spec.indicatorColors = intArrayOf(tintColor)
    val progressIndicatorDrawable =
        IndeterminateDrawable.createCircularDrawable(context, spec)

    this.icon = progressIndicatorDrawable
    this.isEnabled = false
    this.text = "";
    this.iconGravity = MaterialButton.ICON_GRAVITY_TEXT_START
    this.iconPadding = 0;
}

/**
 * Hides the progress bar and restores the original icon of the MaterialButton.
 */
fun MaterialButton.hideProgress(buttonTitle : String) {
    // Restore the original icon by setting it to null or the default icon
    val originalIcon: Drawable? = this.icon  // Save the current icon if needed for later
    this.icon = null  // Remove the progress indicator
    this.text = buttonTitle;
    // Optionally, re-enable the button after progress is completed
    this.isEnabled = true
}