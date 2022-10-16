package wynncraftbombtrack.wynncraftbombtrack;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import wynncraftbombtrack.wynncraftbombtrack.objects.BombObject;

import java.util.ArrayList;

public class WynncraftBombtrack implements ModInitializer {

    @Override
    public void onInitialize() {
        registerEvent();
    }

    public void registerEvent(){
        HudRenderCallback.EVENT.register((matrices, tickDelta) -> {
            doEvent();
        });
    }

    public void doEvent(){
        int y = 100;
        ArrayList<BombObject> removeds = new ArrayList<>();
        for(BombObject bombObject : BombObject.getBombObjects()){
            y += 10;
            MatrixStack m = new MatrixStack();
            MinecraftClient.getInstance().textRenderer.draw(m, bombObject.getText(), 70, y, 0xFFFFFF);
            if(bombObject.shouldRemove()){
                removeds.add(bombObject);
            }
        }
        y = 100;
        BombObject.getBombObjects().removeAll(removeds);
    }
}
