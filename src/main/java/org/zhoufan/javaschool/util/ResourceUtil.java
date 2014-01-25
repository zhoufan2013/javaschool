package org.zhoufan.javaschool.util;

import java.io.*;
import java.net.URI;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: zhoufan
 * Date: 14-1-23 下午3:50
 */
public class ResourceUtil {

	/**
	 * constructor is banned
	 */
	private ResourceUtil(){};

	/**
	 * return thr resolved properties based on the specified encoding
	 * @param file_path properties file`s context path
	 * @param encoding the charset name of encoding
	 * @return {@link java.util.Properties}
	 * @throws Exception
	 */
	public static Properties loadPropertiesFromFilepath(String file_path, String encoding) throws Exception{

		Properties rtn = new Properties();
		InputStreamReader isr = null;
		try{

			isr = new InputStreamReader(loadInputStreamFromFilepath(file_path), encoding);
			rtn.load(isr);

		}catch(IOException ioe){
			throw new IOException("Failed to load propertites from " + file_path , ioe);
		}finally {
			if(null != isr){
				isr.close();
			}
		}
		return rtn;
	}

	/**
	 * return the value to which the specified key is mapped,
	 * or {@link null} if this properties file contains no mapping for the key.
	 * @param file_path properties file`s context path
	 * @param encoding the charset name of encoding
	 * @param key properties
	 * @return {@link java.util.Properties}
	 * @throws Exception
	 */
	public static String resolvePropertiesFromFilepath(String file_path, String encoding, String key) throws Exception{

		Properties rtn = loadPropertiesFromFilepath(file_path, encoding);

		String value = null;
		Set keySet = rtn.keySet();
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()){
			String srcKey = it.next();
			if(org.apache.commons.lang3.StringUtils.equals(key, srcKey)){
				value = rtn.getProperty(srcKey);
			}
		}
		return value;
	}

	/**
	 *
	 * @param file_path properties file`s context path
	 * @return {@link java.io.InputStream}
	 * @throws Exception
	 */
	public static InputStream loadInputStreamFromFilepath(String file_path) throws Exception{
		InputStream is = Thread.currentThread().getClass().getResourceAsStream(file_path);
		return is;
	}

	/**
	 *
	 * @param file_path file location
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String readCfgFile(String file_path, String encoding) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(loadInputStreamFromFilepath(file_path), encoding));

		StringBuilder sb = new StringBuilder();
		String tmp;
		while((tmp = br.readLine()) != null){
			sb.append(tmp);
		}
		br.close();
		return sb.toString().replace("(?<=>)\\s+(?=<)", "");
	}

}
