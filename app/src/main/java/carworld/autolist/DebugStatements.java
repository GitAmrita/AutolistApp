package carworld.autolist;

import android.util.Log;

/**
 * Created by amritachowdhury on 4/22/17.
 */

public class DebugStatements {

    public static void printDebugStatements(String message) {
        if (Config.debug.IS_DEBUG) {
            Log.e(getCallerClassName(), message);
        }
    }

    public static String getCallerClassName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i=1; i<stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(DebugStatements.class.getName()) &&
                    ste.getClassName().indexOf("java.lang.Thread")!= 0) {
                return ste.getClassName();
            }
        }
        return "unknown class";
    }
}
