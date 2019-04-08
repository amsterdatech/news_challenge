import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dutchtechnologies.news_challenge.R
import java.net.URI

//fun Context.isTablet(): Boolean = resources.getBoolean(R.bool.isTablet)
//
//fun Context.isSmartPhone(): Boolean = resources.getBoolean(R.bool.isSmartPhone)
//
//fun Context.isMdpi(): Boolean = resources.getBoolean(R.bool.isMdpi)
//
//fun Context.isHdpi(): Boolean = resources.getBoolean(R.bool.isHdpi)
//
//fun Context.isXhdpi(): Boolean = resources.getBoolean(R.bool.isXhdpi)
//
//fun Context.isXxhdpi(): Boolean = resources.getBoolean(R.bool.isXxhdpi)
//
//fun Context.isXxxhdpi(): Boolean = resources.getBoolean(R.bool.isXxxhdpi)


fun ImageView.load(url: String?, lowQuality: Boolean = true) {
    val options = RequestOptions()
        .priority(Priority.NORMAL)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .format(if (lowQuality) DecodeFormat.PREFER_RGB_565 else DecodeFormat.PREFER_ARGB_8888)
        .placeholder(ColorDrawable(ContextCompat.getColor(this.context, R.color.placeholder)))
        .error(ColorDrawable(ContextCompat.getColor(this.context, R.color.placeholder)))
        .dontAnimate()

    Glide.with(context)
        .load(url)
        .apply(options)
        .into(this@load)
}

fun String.host(): String {
    val uri = URI(this)
    val domain = uri.host;
    return if (domain.startsWith("www.")) domain.substring(4) else domain
}


fun Context.isLandscape(): Boolean = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

fun Context.isPortrait(): Boolean = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT