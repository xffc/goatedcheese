package `fun`.xffc.goatedcheese

import `fun`.xffc.goatedcheese.item.*
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier

const val MOD_ID = "goatedcheese"

class ModEntrypoint: ModInitializer {
    override fun onInitialize() {
        GoatCheeseItem().register("goat_cheese".id())
        GoatMilkBucketItem().register("goat_milk_bucket".id())

        Registry.register(Registries.ITEM_GROUP, "items".id(), FabricItemGroup.builder()
            .displayName(Text.literal("Goated Cheese"))
            .noScrollbar()
            .icon { ItemStack(GoatMilkBucketItem.INSTANCE) }
            .entries { _, entries ->
                entries.add(GoatCheeseItem.INSTANCE)
                entries.add(GoatMilkBucketItem.INSTANCE)
            }
            .build())

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register { group ->
            group.add(GoatCheeseItem.INSTANCE)
            group.add(GoatMilkBucketItem.INSTANCE)
        }
    }
}

fun String.id(): Identifier =
    Identifier.of(MOD_ID, this)

fun Item.register(id: Identifier) {
    Registry.register(Registries.ITEM, id, this)
}