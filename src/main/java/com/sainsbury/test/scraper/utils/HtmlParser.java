package com.sainsbury.test.scraper.utils;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Html Scraper Utility
 * 
 * @author Carlos Cambon
 *
 */
public class HtmlParser {

	// In case the url is not responding swiftly
	private static final int TIMEOUT = 5000;

	public static Document getDocumentFromUrl(String url) throws IOException {
		return Jsoup.connect(url).timeout(TIMEOUT).get();
	}

	/**
	 * Returns the size of the web page requested in kb (no including the assets)
	 * @param webUrl
	 * @return size of the page in kb
	 * @throws IOException
	 */
	public static String getWebPageSize(String webUrl) throws IOException {
		String kbSize = "";
		URLConnection conn = null;
		try {
			URL url = new URL(webUrl);
			conn = url.openConnection();
			long size = conn.getContentLengthLong();
			if (size < 0)
				System.out.println("[HtmlParser] Could not determine file size.\n\n");
			else {
				kbSize = FileUtils.byteCountToDisplaySize(size);
				kbSize = kbSize.replaceAll(" ", "").toLowerCase();
			}
		} finally {
			try {
				conn.getInputStream().close();
			} catch (IOException e) {
				System.out.println("[HtmlParser] Could not close the input stream.\n\n");
				e.printStackTrace();
			}
		}
		return kbSize;
	}
}
