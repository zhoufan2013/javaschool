package org.zhoufan.javaschool.config;

import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.*;
import org.zhoufan.javaschool.util.ResourceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Read system infomation from configuraton xml file.
 *
 * Created with IntelliJ IDEA.
 * User: zhoufan
 * Date: 14-1-24 下午1:27
 */
public class SysInfo {

	private static transient Log log = LogFactory.getLog(SysInfo.class);

	private static final String FILE_PATH = "/javaschool.xml";

	public static HashMap<String, EntityCfg> _cached = new HashMap<String, EntityCfg>();

	private static String language = "";

	/**
	 * constructor is banned.
	 */
	private SysInfo(){}

	public static EntityCfg getEntityCfg(String bobean){

		return _cached.get(bobean);
	}

	static {

		synchronized (_cached){

			try {
				String dataInfo = ResourceUtil.readCfgFile(FILE_PATH, CharEncoding.UTF_8);
				Document doc = DocumentHelper.parseText(dataInfo);

				if(null == doc)
					throw new Exception("Failed in parsing Config File in " + FILE_PATH);

				//load entity info
				List<Element> entities = doc.selectNodes("/system/entities/entity");
				if(null == entities || entities.size() < 1){
					log.error("Entity mapping data is empty!");
				}else{

					EntityCfg entityCfg = null;
					for(Element entity : entities){

						entityCfg = new EntityCfg();
						entityCfg.bobean = entity.attributeValue("bobean");
						entityCfg.type = entity.attributeValue("type");
						entityCfg.seq = entity.attributeValue("seq");
						entityCfg.template = entity.attributeValue("template");

						List<Element> fields = entity.elements();
						if(null != fields && fields.size() > 0){
							for(Element field : fields){
								EntityField entityField = new EntityField();
								entityField.value = field.getText();
								entityField.entityType = field.attributeValue("entityType");
								entityCfg.getFieldList().add(entityField);
							}
						}
						_cached.put(entityCfg.bobean, entityCfg);
					}
				}

				//load language info
				Node elementLang = doc.selectSingleNode("/system/language");
				if(null == elementLang)
					log.error("system language data is empty");
				language = elementLang.getText();

			} catch (DocumentException de) {
				de.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}

	public static String getSysLang(){
		return language;
	}

	public static class EntityCfg{

		public String bobean;
		public String type;
		public String seq;
		public String template;
		public List<EntityField> fieldList;

		public List<EntityField> getFieldList(){
			if(null == fieldList)
				this.fieldList = new ArrayList<EntityField>();
			return this.fieldList;
		}
	}

	public static class EntityField{
		public String entityType;
		public String value;
	}
}
