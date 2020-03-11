package com.ekino.epoxysample

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.epoxy.EpoxyModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var models = emptyList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val controller = ItemController()
        root_recycler_view.setAdapter(controller.getAdapter())

        button_add.setOnClickListener {
            models = models.plus(Item(System.currentTimeMillis(),
                        generateSequence(1) { it + 1 }
                            .take(Random.nextInt(7))
                            .map { "line $it" }.toList()
                    ))
            updateControllerItems(controller)
        }

        updateControllerItems(controller)
    }

    private fun updateControllerItems(controller: ItemController) {
        controller.items = listOf(LoadingRowModel_().id("loading-item"))
        controller.requestModelBuild()
        Handler().postDelayed ({
            controller.items = models.map {
                val itemContentModels: List<EpoxyModel<out View>> = it.content.map {
                    CardSimpleRowModel_()
                        .id(it)
                        .keyText(it)
                        .valueText(it)
                }.plus(
                    CardLastRowModel_()
                        .id(1000)
                        .keyText("LAST")
                        .valueText("ROW")
                )

                CardListViewModel_().id(it.id).models(itemContentModels)
            }
            controller.requestModelBuild()
        }, 1000)
    }
}

data class Item(
        val id: Long,
        val content: List<String>
)