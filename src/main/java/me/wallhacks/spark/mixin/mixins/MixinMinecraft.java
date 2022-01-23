package me.wallhacks.spark.mixin.mixins;

import me.wallhacks.spark.Spark;
import me.wallhacks.spark.event.client.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin({ Minecraft.class })
public class MixinMinecraft {

    @Inject(method = "init", at = @At("RETURN"))
    private void init(final CallbackInfo callbackInfo) {
        //if you ever read this code
        //then return as soon as possible
        //nothing to see down here
        //zero weird ductape fixes that can break at any time down here
        //just continue your day dont look down here

        //it was wallhacks that suggested this not me

        if(Spark.runInShitWay)
    	if(Spark.instance == null)
            Spark.instance = new Spark();
    }


    @Inject(method={"runTickKeyboard"}, at={@At(value="FIELD", target="Lnet/minecraft/client/Minecraft;currentScreen:Lnet/minecraft/client/gui/GuiScreen;", ordinal=0)}, locals= LocalCapture.CAPTURE_FAILSOFT)
    private void onRunTickKeyboard(CallbackInfo ci, int i) {
        if (Keyboard.getEventKeyState() && Spark.eventBus != null) {
            Spark.eventBus.post(new InputEvent(i));
        }
    }

    @Inject(method = "crashed", at = @At("HEAD"))
    public void crashed(CrashReport crash, CallbackInfo callbackInfo) {
        Spark.configManager.Save();
    }

    @Inject(method = "shutdown", at = @At("HEAD"))
    public void shutdown(CallbackInfo callbackInfo) {
        Spark.configManager.Save();
    }
}