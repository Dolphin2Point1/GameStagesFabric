package com.dolphin2point0.game_stages_fabric.item_module.mixin;

import com.dolphin2point0.game_stages_fabric.item_module.ItemModule;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow public abstract ItemStack getStack();

    @Inject(method = "onPlayerCollision", at = @At("HEAD"), cancellable = true)
    public void cancelItemTransfer(PlayerEntity player, CallbackInfo ci) {
        if (!this.world.isClient()) {
            ItemStack itemStack = this.getStack();
            Item item = itemStack.getItem();
            if(!ItemModule.itemStageChecker.itemUsableByPlayer(player, item)) {
                ci.cancel();
            }
        }
    }
}
