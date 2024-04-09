package fpoly.htdshoes_pro1121.d;

import android.content.Context;
import android.widget.Toast;

public class ShowMessageHelper {
    public static void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
