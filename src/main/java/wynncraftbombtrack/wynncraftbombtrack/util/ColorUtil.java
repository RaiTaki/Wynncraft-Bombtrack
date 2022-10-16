package wynncraftbombtrack.wynncraftbombtrack.util;

import io.github.darkkronicle.darkkore.util.Color;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ColorUtil {

    public static Color parse(String color) {
        try {
            return new Color(Integer.parseInt(color));
        } catch (NumberFormatException ignored) {
        }

        if (color.startsWith("#")) {
            color = color.substring(1);
        } else if(color.startsWith("0x")) {
            color = color.substring(2);
        } if(color.length() == 6) {
            color = "FF" + color;
        } else if (color.length() != 8) {
            return ERROR;
        }
        try {
            return new Color(Integer.valueOf(color.substring(2, 4), 16),
                    Integer.valueOf(color.substring(4, 6), 16),
                    Integer.valueOf(color.substring(6, 8), 16),
                    Integer.valueOf(color.substring(0, 2), 16));
        } catch (NumberFormatException error) {
            return ERROR;
        }
    }


    public static Color WHITE = new Color(255, 255, 255, 255);
    public static Color BLACK = new Color(0, 0, 0, 255);
    public static Color GRAY = new Color(128, 128, 128, 255);
    public static Color DARK_GRAY = new Color(49, 51, 53, 255);
    public static Color SELECTOR_RED = new Color(191, 34, 34, 255);
    public static Color SELECTOR_GREEN = new Color(53, 219, 103, 255);
    public static Color SELECTOR_BLUE = new Color(51, 153, 255, 255);
    public static Color ERROR = new Color(255, 0, 255, 255);

    /**
     * Blends two {@link Color}s based off of a percentage.
     *
     * @param original   color to start the blend with
     * @param blend      color that when fully blended, will be this
     * @param percentage the percentage to blend
     * @return the simple color
     */


}
