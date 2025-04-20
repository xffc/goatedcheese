package fun.xffc.goatedcheese.mixin;

import fun.xffc.goatedcheese.ModItems;
import fun.xffc.goatedcheese.item.GoatMilkBucketItem;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GoatEntity.class)
abstract class GoatEntityMixin {
    @ModifyArg(
            method = "interactMob",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemUsage;exchangeStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"
            ),
            index = 2
    )
    private ItemStack changeStack(ItemStack itemStack, PlayerEntity playerEntity, ItemStack itemStack2) {
        return ModItems.GOAT_MILK_BUCKET.getDefaultStack();
    }
}
