package `fun`.xffc.goatedcheese

import `fun`.xffc.goatedcheese.item.*
import net.minecraft.component.DataComponentTypes
//? if 1.21.4 {
import net.minecraft.component.type.ConsumableComponents
//?}
import net.minecraft.component.type.FoodComponents
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import java.util.function.Function

object ModItems {
    private fun register(id: Identifier, factory: Function<Item.Settings, Item>, settings: Item.Settings): Item {
        val key = RegistryKey.of(RegistryKeys.ITEM, id)
        //? if 1.21.4 {
        settings.registryKey(key)
        //?}
        return Registry.register(Registries.ITEM, key, factory.apply(settings))
    }

    @JvmField
    val GOAT_CHEESE = register(
        "goat_cheese".id(),
        { Item(it) },
        Item.Settings()
            .food(FoodComponents.APPLE)
    )

    @JvmField
    val GOAT_MILK_BUCKET = register(
        "goat_milk_bucket".id(),
        {
            //? if 1.21.4 {
            Item(it)
            //?} else if 1.21.1 {
            //GoatMilkBucketItem(it)
            //?}
        },
        Item.Settings()
            //? if 1.21.4 {
            .recipeRemainder(Items.BUCKET)
            .component(DataComponentTypes.CONSUMABLE, ConsumableComponents.MILK_BUCKET)
            .useRemainder(Items.BUCKET)
            //?}
            .maxCount(1)
    )
}