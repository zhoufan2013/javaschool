import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zhoufan.javaschool.config.I18NFactory;

/**
 * Created by IntelliJ IDEA.
 * User: zhoufan
 * Date: 14-1-23 下午4:00
 */
public class TestUtil {

	private static transient Log log = LogFactory.getLog(TestUtil.class);

	private static final String FILE_PATH = "/i18n/javaschool_i18n_EN_US.properties";

	private static final String encoding = "UTF-8";

	public static void main(String[] args) throws Exception{
//		Properties properties = ResourceUtil.loadPropertiesFromFilepath(FILE_PATH, encoding);
//		log.info(properties.get("test_key_1"));

//		SysInfo.EntityCfg entityCfg = SysInfo.getEntityCfg("BOUPRoleBean");
//		log.info(entityCfg.type);

//		log.info(SysInfo.getSysLang());

		log.info(I18NFactory.getI18nMsg("test_key_2"));
	}
}
