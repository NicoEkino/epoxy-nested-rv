package com.ekino.epoxysample

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CardLastRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val key: TextView
    private val value: TextView

    init {
        inflate(context, R.layout.card_last_row, this)
        key = findViewById(R.id.text_name)
        value = findViewById(R.id.text_value)
    }

    @TextProp
    fun setKeyText(keyText: CharSequence) {
        key.text = keyText
    }

    @TextProp
    fun setValueText(valueText: CharSequence) {
        value.text = valueText
    }
}
