package com.dutchtechnologies.news_challenge.articles

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import com.dutchtechnologies.news_challenge.R

class CustomViewCategoryTag : AppCompatTextView {
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        build()
    }

    private fun build() {
//        setBackgroundColor(ContextCompat.getColor(context, R.color.custom_category_tag_semi_transparent))
        setTextColor(ContextCompat.getColor(context, R.color.placeholder))
        setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(R.dimen.text_size_twelve))
//        setPadding(
//            context.resources.getDimensionPixelSize(R.dimen.spacings_eight),
//            context.resources.getDimensionPixelSize(R.dimen.spacings_two),
//            context.resources.getDimensionPixelSize(R.dimen.spacings_eight),
//            context.resources.getDimensionPixelSize(R.dimen.spacings_two)
//        )

        text = context.getString(R.string.category_placeholder)
        isAllCaps = true
        gravity = Gravity.LEFT
        setTypeface(typeface, Typeface.BOLD)
    }
}