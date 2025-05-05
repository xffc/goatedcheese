package `fun`.xffc.goatedcheese.block

import com.mojang.serialization.MapCodec
import `fun`.xffc.goatedcheese.ModBlocks
import `fun`.xffc.goatedcheese.ModItems
import `fun`.xffc.goatedcheese.item.GoatCheeseItem
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.SidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

class CheeseFactoryBlock(settings: Settings): BlockWithEntity(settings) {
    override fun getCodec(): MapCodec<out BlockWithEntity?>? {
        TODO("Not yet implemented")
    }

    override fun createBlockEntity(
        pos: BlockPos,
        state: BlockState
    ): BlockEntity = CheeseFactoryBlockEntity(pos, state)
}

class CheeseFactoryBlockEntity(pos: BlockPos, state: BlockState):
    BlockEntity(ModBlocks.CHEESE_FACTORY_ENTITY, pos, state),
    SidedInventory
{
    private var processItem: ItemStack = ItemStack.EMPTY

    override fun getAvailableSlots(side: Direction?): IntArray? =
        IntArray(1)

    override fun canInsert(
        slot: Int,
        stack: ItemStack?,
        dir: Direction?
    ): Boolean =
        stack != null && stack.item is GoatCheeseItem

    override fun canExtract(
        slot: Int,
        stack: ItemStack,
        dir: Direction?
    ): Boolean = processItem.item is
    override fun size(): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getStack(slot: Int): ItemStack? {
        TODO("Not yet implemented")
    }

    override fun removeStack(slot: Int, amount: Int): ItemStack? {
        TODO("Not yet implemented")
    }

    override fun removeStack(slot: Int): ItemStack? {
        TODO("Not yet implemented")
    }

    override fun setStack(slot: Int, stack: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun canPlayerUse(player: PlayerEntity?): Boolean {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

}