package bestaveiro.appsummercourse;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class ContactsChild {

    private String name = "";
    private Drawable drawable;
    private Drawable drawableFlags;


    public ContactsChild(String name, Drawable drawable, Drawable drawableFlags) {
        super();
        this.name = name;
        this.drawableFlags = drawableFlags;
        this.drawable = drawable;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Drawable getDrawable() {
        return drawable;
    }
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public Drawable getDrawableFlags() {
        return drawableFlags;
    }
    public void setDrawableFlags(Drawable drawableFlags) {
        this.drawableFlags = drawableFlags;

    }



}