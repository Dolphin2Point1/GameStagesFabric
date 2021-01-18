package com.dolphin2point0.game_stages_fabric.item_module.mixin;

import com.dolphin2point0.game_stages_fabric.item_module.ItemModule;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin extends Screen {
    private static final Identifier RED_TINT = new Identifier("item_stage", "textures/gui/red_tint.png");

    protected HandledScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "drawSlot", at = @At("HEAD"))
    public void drawSlot(MatrixStack matrices, Slot slot, CallbackInfo info) {
        if(client != null && !ItemModule.itemStageChecker.itemUsableByPlayer(client.player, slot.getStack().getItem())) {
            client.getTextureManager().bindTexture(RED_TINT);
            drawTexture(matrices, slot.x, slot.y, 0, 0, 16, 16);
        }
    }
}