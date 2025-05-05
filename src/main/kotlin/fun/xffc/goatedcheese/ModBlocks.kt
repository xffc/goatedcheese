package `fun`.xffc.goatedcheese

import `fun`.xffc.goatedcheese.block.CheeseFactoryBlock
import `fun`.xffc.goatedcheese.block.CheeseFactoryBlockEntity
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier
import java.util.function.Function

object ModBlocks {
    private fun register(id: Identifier, factory: Function<AbstractBlock.Settings, Block>, settings: AbstractBlock.Settings): Block {
        val key = RegistryKey.of(Registries.BLOCK.key, id)
        return Registry.register(Registries.BLOCK, key, factory.apply(settings.registryKey(key)))
    }

    private fun <T: BlockEntity> registerEntity(id: Identifier, factory: FabricBlockEntityTypeBuilder.Factory<T>, block: Block): BlockEntityType<T> =
        Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            id,
            FabricBlockEntityTypeBuilder.create<T>(factory, block)
                .build())

    @JvmField
    val CHEESE_FACTORY = register(
        "cheese_factory".id(),
        { CheeseFactoryBlock(it) },
        AbstractBlock.Settings.create()
    )

    @JvmField
    val CHEESE_FACTORY_ENTITY = registerEntity(
        "cheese_factory".id(),
        { pos, state -> CheeseFactoryBlockEntity(pos, state) },
        CHEESE_FACTORY
    )
}