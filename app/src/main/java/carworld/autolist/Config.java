package carworld.autolist;

/**
 * Created by amritachowdhury on 4/22/17.
 */

public class Config {

    public static final class debug {
        public static final boolean IS_DEBUG = true;
    }

    public static final class priceRange {
        public static final int MULTIPLIER = 1000;
        public static final int INSTALLMENT_PERIOD_IN_MONTHS = 36;
    }

    public static final class recycleView {
        public static final int VISIBLE_THRESHOLD = 5;
        public static final String SELECTED_VEHICLE = "selectedVehicle";
    }

    public static final class api {
        public static final String BASE_URL = "https://autolist-test.herokuapp.com/search";
    }
}
