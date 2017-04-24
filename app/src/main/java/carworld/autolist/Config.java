package carworld.autolist;

/**
 * Created by amritachowdhury on 4/22/17.
 */

public class Config {

    public static final class debug {
        public static final boolean IS_DEBUG = false;
    }

    public static final class priceRange {
        public static final int MULTIPLIER = 1000;
        public static final int INSTALLMENT_PERIOD_IN_MONTHS = 36;
    }

    public static final class recycleView {
        public static final int VISIBLE_THRESHOLD = 5;
        public static final String SELECTED_VEHICLE = "selectedVehicle";
        public static final int ASK_SEARCH_RESULT_SAVED = 19;
    }

    public static final class api {
        public static final String BASE_URL = "https://autolist-test.herokuapp.com/search";
    }

    public static final class testData {
        public static final String TEST_EMAIL = "test_me@mailinator.com";
        public static final String TEST_PHONE_NUMBER = "6772332910";
        public static final int TEST_NO_1 = 3;
        public static final int TEST_NO_2 = 7;
        public static final int TEST_NO_3 = 13;
    }

    public static final class permission {
        public static final int REQUEST_READ_PHONE_STATE = 101;
    }

    public static final class intent {
        public static final int CONTACT_DEALER = 1;
    }

    public static final class sharedPreference {
        public static final String USERNAME = "username";
        public static final String PASSWORD_HASHED = "passwordHashed";
        public static final String IS_LOGGEDIN = "isLoggedIn";
        public static final String SALT = "login11324";
    }
}
