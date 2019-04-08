import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dutchtechnologies.news_challenge.R
import com.dutchtechnologies.news_challenge.model.SearchRequestForm
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

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

inline fun <reified T : Any> Fragment.extra(key: String, defaultValue: T): T? {
    return when (T::class) {
        String::class -> arguments?.getString(key) as T?
        Int::class -> arguments?.getInt(key, defaultValue as Int) as T?
        Boolean::class -> arguments?.getBoolean(key, defaultValue as Boolean) as T?
        Float::class -> arguments?.getFloat(key, defaultValue as Float) as T?
        Long::class -> arguments?.getLong(key, defaultValue as Long) as T?
        SearchRequestForm::class -> arguments?.getParcelable<SearchRequestForm>(key) as T
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}


fun Context.isLandscape(): Boolean = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

fun Context.isPortrait(): Boolean = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

fun Date.formatToDayMonthName(): String {
    val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return sdf.format(this)
}

fun String.parseIsoDateFormat(): Date {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.parse(this)
}