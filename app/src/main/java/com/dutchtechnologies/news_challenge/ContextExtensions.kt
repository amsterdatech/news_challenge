import android.content.Context
import android.content.res.Configuration
import com.dutchtechnologies.news_challenge.R

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

fun Context.isLandscape(): Boolean = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

fun Context.isPortrait(): Boolean = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT