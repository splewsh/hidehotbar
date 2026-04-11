package splewsh.hidehotbar.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.CameraType;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.DeltaTracker;
import splewsh.hidehotbar.HideHotbarConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class InGameHudMixin {

    @Inject(
            method = "extractHotbarAndDecorations",
            at = @At("HEAD"),
            cancellable = true
    )
    private void hideHotbarInThirdPerson(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        CameraType camera = Minecraft.getInstance().options.getCameraType();
        HideHotbarConfig config = HideHotbarConfig.get();

        if (camera == CameraType.FIRST_PERSON && config.hideInFirstPerson) {
            ci.cancel();
        } else if (camera == CameraType.THIRD_PERSON_BACK && config.hideInThirdPersonBack) {
            ci.cancel();
        } else if (camera == CameraType.THIRD_PERSON_FRONT && config.hideInThirdPersonFront) {
            ci.cancel();
        }
    }
}