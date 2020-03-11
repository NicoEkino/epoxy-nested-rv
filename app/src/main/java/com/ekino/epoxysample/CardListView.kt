package com.ekino.epoxysample

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.OnViewRecycled

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CardListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val epoxyRecyclerView: RecyclerView
    private val controller: EpoxyController
    private var items: List<EpoxyModel<*>> = emptyList()

    init {
        inflate(context, R.layout.card_list_view, this)
        epoxyRecyclerView = findViewById(R.id.list_view)
        epoxyRecyclerView.layoutManager = object : LinearLayoutManager(context) {
            @Override
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        controller = object : EpoxyController() {
            override fun buildModels() {
                items.forEach {
                    add(it)
                }
            }
        }
        epoxyRecyclerView.adapter = controller.adapter
    }

    @OnViewRecycled
    fun clear(){
        epoxyRecyclerView.removeAllViews()
        invalidate()
    }

    @ModelProp
    fun setModels(models: List<EpoxyModel<out View>>) {
        epoxyRecyclerView.removeAllViews()
        items = models
        controller.requestModelBuild()
    }
}
