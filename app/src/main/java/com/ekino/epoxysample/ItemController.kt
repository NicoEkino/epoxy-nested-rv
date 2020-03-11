package com.ekino.epoxysample

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel

class ItemController : EpoxyController() {


    var items: List<EpoxyModel<*>> = emptyList()
    override fun buildModels() {
        items.forEach {
            add(it)
        }
    }


}