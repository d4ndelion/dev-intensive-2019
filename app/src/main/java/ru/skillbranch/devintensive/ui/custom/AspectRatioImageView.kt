package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.util.AttributeSet
import ru.skillbranch.devintensive.R

class AspectRatioImageView (
    context: Context,
    attributes: AttributeSet? = null,
    defineStyleAttribute: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(
    context,
    attributes,
    defineStyleAttribute // any View takes 3 parameters
) {
    companion object {
        private const val DEFAULT = 1.78f // default side ratio 16:9
    }

    private var ratio = DEFAULT

    init {
        if (attributes != null){
            val attr = context.obtainStyledAttributes(attributes, R.styleable.AspectRatioImageView)
            ratio = attr.getFloat(R.styleable.AspectRatioImageView_ratio, DEFAULT)
            attr.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val newHeight = (measuredWidth/ratio).toInt()
        setMeasuredDimension(measuredWidth, newHeight)
    }
}