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
        "raw_goat_cheese".id(),
        { GoatCheeseItem(it) },
        Item.Settings()
            .food(FoodComponents.APPLE)
    )

    @JvmField
    val GOAT_CHEESE_MASS = register(
        "goat_cheese_mass".id(),
        { Item(it) },
        Item.Settings()
            .food(FoodComponents.DRIED_KELP)
    )

    @JvmField
    val RIPENED_GOAT_CHEESE = register(
        "RIPENED_GOAT_CHEESE".id(),
        { RipenedGoatCheeseItem(it) },
        Item.Settings()
            .food(FoodComponents.COOKED_CHICKEN)
    )

    @JvmField
    val GOAT_MILK_BUCKET = register(
        "goat_milk_bucket".id(),
        { Item(it) },
        Item.Settings()
            //? if 1.21.4 {
            .recipeRemainder(Items.BUCKET)
            .component(DataComponentTypes.CONSUMABLE, ConsumableComponents.MILK_BUCKET)
            .useRemainder(Items.BUCKET)
            //?}
            .maxCount(1)
    )
}