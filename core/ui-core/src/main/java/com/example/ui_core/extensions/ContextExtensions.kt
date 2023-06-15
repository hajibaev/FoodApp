package com.example.ui_core.extensions

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import de.hdodenhof.circleimageview.CircleImageView

fun Context.makeToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun Context.getAttrColor(attrId: Int): Int {
    val attrs = intArrayOf(attrId)
    val array = this.obtainStyledAttributes(attrs)
    return array.getColor(0, 0)
}

private fun shimmerDrawable(): ShimmerDrawable {
    val shimmer =
        Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
            .setDuration(1800) // how long the shimmering animation takes to do one full sweep
            .setBaseAlpha(0.7f) //the alpha of the underlying children
            .setHighlightAlpha(0.6f) // the shimmer alpha amount
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setAutoStart(true)
            .build()

    // This is the placeholder for the imageView
    return ShimmerDrawable().apply {
        setShimmer(shimmer)
    }
}

fun Context.showRoundedImage(
    roundedSize: Int = 8.toDp,
    imageUrl: String,
    imageView: ImageView,
) {
    val requestOptions = RequestOptions()
        .transforms(CenterCrop(), RoundedCorners(roundedSize))
        .timeout(3000)
        .placeholder(shimmerDrawable())
    Glide.with(this)
        .load(imageUrl)
        .apply(requestOptions)
        .into(imageView)
}

