package bestaveiro.appsummercourse;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class ContactsChild {

    private String name = "";
    private Drawable drawable;

    public ContactsChild(String name, Drawable drawable) {
        super();
        this.name = name;
        this.drawable =drawable;
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


}