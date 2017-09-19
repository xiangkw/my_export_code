package com.my.export.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	public static void deleteFile(String path) {
		deleteFile(new File(path));
	}

	public static void deleteFile(File file) {
		if (!file.exists()) {
			return;
		}
		if (file.isFile()) {
			file.delete();
		} else {
			File[] files = file.listFiles();
			if (files.length > 0) {
				for (File f : files) {
					deleteFile(f);
				}
			}
			file.delete();
		}
	}

	@SuppressWarnings("resource")
	public static List<String> readFile(File file) throws Exception {

		InputStreamReader read = null;
		BufferedReader reader = null;

		read = new InputStreamReader(new FileInputStream(file));
		reader = new BufferedReader(read, 2 * 1048576);

		String line = "";
		List<String> lines = new ArrayList<String>();

		while ((line = reader.readLine()) != null) {

			/********* start 解析一行数据 ************/
			try {
				lines.add(line);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lines;

	}

	public static void writeToFile(String filePath, String str) throws Exception {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		} else {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
		}
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(filePath);
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
