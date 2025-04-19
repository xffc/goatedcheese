package `fun`.xffc.goatedcheese.item

import net.minecraft.component.type.FoodComponents
import net.minecraft.item.Item

class GoatCheeseItem: Item(Settings()
    .food(FoodComponents.APPLE)
) {
    init {
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: GoatCheeseItem
    }
}