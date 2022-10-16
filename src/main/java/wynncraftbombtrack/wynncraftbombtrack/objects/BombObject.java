package wynncraftbombtrack.wynncraftbombtrack.objects;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextHandler;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;

public class BombObject {

    private static ArrayList<BombObject> bombObjects = new ArrayList<>();

    private String server;
    private BOMB_TYPE type;
    private long date;

    public BombObject(String server, BOMB_TYPE type, long date) {
        this.server = server;
        this.type = type;
        this.date = date;

        bombObjects.add(this);
    }

    public String getFormattedType(){
        String pattern = "mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date(getCalculatedDate()));
    }

    public long getCalculatedDate(){
        return (date + 20*60*1000) - System.currentTimeMillis();
    }

    public boolean shouldRemove(){
        long time = getCalculatedDate();
        return time < 0;
    }

    public String getText(){
        return "§e" + type.toString().replace("_", " ") + " bomb on " + server + " - " + getFormattedType();
    }

    public static BOMB_TYPE stringToBombType(String type){
        if(type.equalsIgnoreCase("combat"))
            return BOMB_TYPE.COMBAT_XP;
        else if(type.equalsIgnoreCase("PROFESSION XP"))
            return BOMB_TYPE.PROFESSION_XP;
        else if(type.equalsIgnoreCase("PROFESSION speed"))
            return BOMB_TYPE.PROFESSION_SPEED;
        else if(type.equalsIgnoreCase("dungeon"))
            return BOMB_TYPE.DUNGEON;
        else if(type.equalsIgnoreCase("loot"))
            return BOMB_TYPE.LOOT;

        return null;
    }

    public static ArrayList<BombObject> getBombObjects() {
        return bombObjects;
    }

    public String getServer() {
        return server;
    }

    public BOMB_TYPE getType() {
        return type;
    }

    public long getDate() {
        return date;
    }

    public enum BOMB_TYPE{
        PROFESSION_XP, PROFESSION_SPEED, COMBAT_XP, DUNGEON, LOOT
    }
}
