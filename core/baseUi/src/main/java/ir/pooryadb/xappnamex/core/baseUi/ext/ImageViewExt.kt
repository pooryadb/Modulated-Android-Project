package ir.pooryadb.xappnamex.core.baseUi.ext

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import ir.pooryadb.xappnamex.core.baseUi.R
import ir.pooryadb.xappnamex.core.baseUi.utils.glide.GlideUtils

fun ImageView.clear() {
    this.setImageDrawable(null)
}

fun ImageView.loadCompat(
    url: Any,
    @DrawableRes placeholderRes: Int? = R.drawable.ic_round_image_24,
    @DrawableRes errorRes: Int? = R.drawable.ic_baseline_image_not_supported_24,
) {
    if (url is String && url.toString().contains(".svg"))
        loadSVG(url, placeholderRes, errorRes)
    else loadDrawable(url, placeholderRes, errorRes)
}

@SuppressLint("CheckResult")
private fun ImageView.loadSVG(
    url: Any,
    @DrawableRes placeholderRes: Int? = R.drawable.ic_round_image_24,
    @DrawableRes errorRes: Int? = R.drawable.ic_baseline_image_not_supported_24,
) {
    GlideUtils(context).getSVGRequestBuilder(null)
        .load(url)
        .apply {
            placeholderRes?.let { placeholder(it) }
            errorRes?.let { error(it) }
        }.into(this)
}


private fun ImageView.loadDrawable(
    url: Any,
    @DrawableRes placeholderRes: Int? = R.drawable.ic_round_image_24,
    @DrawableRes errorRes: Int? = R.drawable.ic_baseline_image_not_supported_24,
) {
    GlideUtils(context).getDrawableRequestBuilder(null)
        .load(url)
        .apply {
            placeholderRes?.let { placeholder(it) }
            errorRes?.let { error(it) }
        }.into(this)
}

fun Context.getBitmap(
    url: Any,
    result: ((Bitmap) -> Unit),
    @DrawableRes placeholderRes: Int? = R.drawable.ic_round_image_24,
    @DrawableRes errorRes: Int? = R.drawable.ic_baseline_image_not_supported_24,
    requestOptions: RequestOptions? = null
) {
    GlideUtils(this).getBitmapRequestBuilder(requestOptions)
        .load(url)
        .apply {
            placeholderRes?.let { placeholder(it) }
            error(errorRes)
        }
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                result.invoke(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }

        })

}