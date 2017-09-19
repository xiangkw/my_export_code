/**
 * 
 */
package com.my.export.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.my.export.model.DbConfig;

/**
 * 数据库配置类
 * 
 * @author xiangkaiwei
 *
 */
public class DbConfigUtils {

	private static final String xmlPath = "/db-config.xml";

	private static Map<String, DbConfig> dbMap;

	private static List<DbConfig> dbList;

	private DbConfigUtils() {
	}

	/**
	 * 初始化方法
	 */
	private synchronized static void initParamsFactory() {
		try {
			dbMap = new LinkedHashMap<>();
			dbList = new ArrayList<>();

			parseDom();

			System.out.println("初始化表格成功，解析到数据库个数：" + dbMap.size());

		} catch (Exception e) {
			dbMap = null;
			dbList = null;
			System.out.println("初始化表格异常，异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 解析dom节点
	 */
	private static void parseDom() throws Exception {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		Document document = db.parse(ResourceUtil.getResourceAsStream(xmlPath));
		Element rootElement = document.getDocumentElement();

		NodeList childrenNode = rootElement.getChildNodes();
		for (int i = 0; i < childrenNode.getLength(); i++) {

			Node node = childrenNode.item(i);

			// getChildNodes()方法可能返回空白的，所以需要做一次判定
			if (node instanceof Element) {
				parseElement((Element) node);
			}
		}
	}

	private static void parseElement(Element element) throws Exception {
		String nodeName = element.getNodeName();
		if ("db".equalsIgnoreCase(nodeName)) {
			String dbId = element.getAttribute("id");
			String desc = element.getAttribute("desc");

			DbConfig dbConfig = new DbConfig(dbId, desc);
			NodeList childrenNode = element.getChildNodes();
			for (int i = 0; i < childrenNode.getLength(); i++) {

				Map<String, String> subMap = new HashMap<>();
				Node node = childrenNode.item(i);
				if (node instanceof Element) {
					Element subElement = (Element) node;
					String name = subElement.getAttribute("name");
					String value = subElement.getAttribute("value");

					subMap.put(name, value);
				}

				dbConfig.setAttr(subMap);
			}

			dbMap.put(dbId, dbConfig);
			dbList.add(dbConfig);
		}
	}

	public static DbConfig getDbConfigById(String dbId) {
		if (dbId == null) {
			return null;
		}
		if (dbMap == null) {
			initParamsFactory();
		}
		return dbMap.get(dbId);
	}

	public static List<DbConfig> getAllDbConfig() {
		if (dbList == null) {
			initParamsFactory();
		}
		return dbList;
	}

	public static void main(String[] args) {

		DbConfig DbConfig = getDbConfigById("01");
		System.out.println(DbConfig.getDesc());
	}
}
