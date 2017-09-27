package andlab.com.ecomtest.helper;

/**
 * Created by admin on 7/24/2017.
 */

public interface DrawableClickListener {
    public static enum DrawablePosition { TOP, BOTTOM, LEFT, RIGHT };
    public void onClick(DrawablePosition target);

}
