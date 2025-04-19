package `fun`.xffc.goatedcheese.item

import net.minecraft.advancement.criterion.Criteria
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsage
import net.minecraft.item.Items
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.UseAction
import net.minecraft.world.World

class GoatMilkBucketItem: Item(Settings().maxCount(1)) {
    init {
        INSTANCE = this
    }

    override fun finishUsing(stack: ItemStack, world: World, user: LivingEntity): ItemStack {
        if (user is ServerPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(user, stack);
            user.incrementStat(Stats.USED.getOrCreateStat(this))
        }

        if (!world.isClient) {
            user.clearStatusEffects()
        }

        if (user is PlayerEntity) {
            return ItemUsage.exchangeStack(stack, user, ItemStack(Items.BUCKET), false)
        } else {
            stack.decrementUnlessCreative(1, user)
            return stack
        }
    }

    override fun getMaxUseTime(stack: ItemStack, user: LivingEntity): Int =
        MAX_USE_TIME

    override fun getUseAction(stack: ItemStack): UseAction =
        UseAction.DRINK

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> =
        ItemUsage.consumeHeldItem(world, user, hand)

    companion object {
        lateinit var INSTANCE: GoatMilkBucketItem

        private const val MAX_USE_TIME = 32
    }
}