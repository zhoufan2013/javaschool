package org.zhoufan.javaschool.config;

import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zhoufan.javaschool.util.ResourceUtil;

import java.util.Properties;

/**
 * Creat I18N Message
 *
 * Created with IntelliJ IDEA.
 * User: zhoufan
 * Date: 14-1-24 下午3:43
 */
public class I18NFactory {

	private static transient Log log = LogFactory.getLog(I18NFactory.class);

	private static Properties i18n_info;

	/**
	 * constructor is banned.
	 */
	private I18NFactory(){}

	static{
		loadI18NResource(SysInfo.getSysLang());
	}

	public static String getI18nMsg(String key){
		if(StringUtils.isEmpty(key))
			return StringUtils.EMPTY;
		return i18n_info.getProperty(key);
	}

	public static void loadI18NResource(String locale){
		try {
			if(log.isDebugEnabled()){
				log.info("start to load i18n resource...");
			}
			i18n_info = ResourceUtil.loadPropertiesFromFilepath("/i18n/javaschool_i18n_" + locale + ".properties", CharEncoding.UTF_8);
			if(log.isDebugEnabled()){
				log.info(i18n_info.size() + " counts of i18n " + locale +" resource loaded...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}