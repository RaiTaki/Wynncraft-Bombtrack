package wynncraftbombtrack.wynncraftbombtrack.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wynncraftbombtrack.wynncraftbombtrack.objects.BombObject;

import java.util.UUID;

@Mixin(InGameHud.class)
public class MessageMixin {

    @Inject(method = "addChatMessage", at = @At("HEAD"))
    private void getRaw(MessageType type, Text message, UUID sender, CallbackInfo ci) {
        doBomb(message.getString());
    }

    public void doBomb(String message){
        System.out.println(message);
        if(message.contains("[Bomb Bell]")){
            String[] split = message.replace("[Bomb Bell]", "").split(" ");
            if(split.length < 9 || split.length > 10) return;

            String server = split[8];
            String type = split[5];
            if(type.equalsIgnoreCase("profession"))
                type = split[5] + " " + split[6];
            if(split.length == 10)
                server = split[9];

            new BombObject(server, BombObject.stringToBombType(type), System.currentTimeMillis());
        }
    }
}
