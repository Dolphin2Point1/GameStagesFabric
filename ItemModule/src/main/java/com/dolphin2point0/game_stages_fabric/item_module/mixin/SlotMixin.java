package com.dolphin2point0.game_stages_fabric.item_module.mixin;

import com.dolphin2point0.game_stages_fabric.item_module.ItemModule;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Slot.class)
public abstract class SlotMixin {
    @Shadow public abstract ItemStack getStack();

    @Inject(method = "canTakeItems", at = @At("HEAD"), cancellable = true)
    public void canTakeItems(PlayerEntity playerEntity, CallbackInfoReturnable<Boolean> cir) {
        if(!ItemModule.itemStageChecker.itemUsableByPlayer(playerEntity, this.getStack().getItem())) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
