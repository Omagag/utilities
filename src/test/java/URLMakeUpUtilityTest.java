import com.bbva.intranet.utilities.URLMakeUpUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class URLMakeUpUtilityTest {

    private static final Logger logger = LoggerFactory.getLogger(URLMakeUpUtilityTest.class);

    public static void main(String... args) {
        String url = URLMakeUpUtility.changePortForJunction("https://tv.intranet.com.mx:6410/controlFacultadesDC", "loginMiPortal");
        logger.info(url);
        logger.info(URLMakeUpUtility.changeDomain(url, "https://www.miportalwiffi.mex.bancomer.com"));

    }

}
