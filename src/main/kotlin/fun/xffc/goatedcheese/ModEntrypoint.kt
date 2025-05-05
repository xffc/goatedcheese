package `fun`.xffc.goatedcheese

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.`object`.builder.v1.trade.TradeOfferHelper
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.village.TradeOffer
import net.minecraft.village.TradeOffers
import net.minecraft.village.TradedItem
import net.minecraft.village.VillagerProfession

const val MOD_ID = "goatedcheese"

class ModEntrypoint: ModInitializer {
    override fun onInitialize() {
        ModItems // waking this shit up
        ModBlocks // and this too

        TradeOffers.PROFESSION_TO_LEVELED_TRADE[VillagerProfession.FARMER].put()

        Registry.register(Registries.ITEM_GROUP, "items".id(), FabricItemGroup.builder()
            .displayName(Text.literal("Goated Cheese"))
            .noScrollbar()
            .icon { ModItems.GOAT_CHEESE.defaultStack }
            .entries { _, entries ->
                entries.add(ModItems.GOAT_CHEESE)
                entries.add(ModItems.GOAT_MILK_BUCKET)
            }
            .build())

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register { group ->
            group.add(ModItems.GOAT_CHEESE)
            group.add(ModItems.GOAT_MILK_BUCKET)
        }
    }
}

fun String.id(): Identifier =
    Identifier.of(MOD_ID, this)

fun Item.register(id: Identifier) {
    Registry.register(Registries.ITEM, id, this)
}