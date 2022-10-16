package wynncraftbombtrack.wynncraftbombtrack;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.font.TextVisitFactory;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.math.AffineTransformation;
import net.minecraft.util.math.Matrix4f;
import wynncraftbombtrack.wynncraftbombtrack.objects.BombObject;

import java.awt.*;
import java.util.ArrayList;

public class WynncraftBombtrack implements ModInitializer {

    public static String MOD_ID = "wynncraftbombtrack";

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
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        for(BombObject bombObject : BombObject.getBombObjects()){
            y += 11;
            MatrixStack m = new MatrixStack();
            DrawableHelper.fill(m, 10, y-1, 10 + textRenderer.getWidth(bombObject.getText()), y + 10, 0x60000000);
            textRenderer.drawWithShadow(m, bombObject.getText(), 10, y, 0);
            if(bombObject.shouldRemove()){
                removeds.add(bombObject);
            }

        }
        y = 100;
        BombObject.getBombObjects().removeAll(removeds);

        //MinecraftClient.getInstance().setScreen(new EditScreen(Text.of("aaaa")));
    }

}
