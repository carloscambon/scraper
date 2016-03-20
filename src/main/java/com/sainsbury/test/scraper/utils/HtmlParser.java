package com.sainsbury.test.scraper.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sainsbury.test.scraper.beans.Product;
import com.sainsbury.test.scraper.beans.ProductsResponse;

public class HtmlParser {

	// In case the url is not responding swiftly
	private static final int TIMEOUT = 5000;
	private static NumberFormat df = NumberFormat.getInstance(Locale.UK);

	{
		df.setMinimumFractionDigits(2);
	}

	public static List<Product> getProductList(String url) throws IOException {
		Document doc = urlGet(url);
		Elements products = doc.select("div.product ");

		List<Product> productsList = new ArrayList<Product>();
		for (Element productElement : products) {

			// Getting the product title and price per unit
			Elements title = productElement.select("div.productInfo").select("h3").select("a");
			String productTitle = title.get(0).text();

			Elements price_unit1 = productElement.select("div.addToTrolleytabBox").select("div.pricing")
					.select("p.pricePerUnit");
			float productPricePerUnit = extractPricePerUnit(price_unit1.get(0).textNodes().get(0));

			// Grabbing the link on the title to get the size of that page (no assets)
			String href = title.get(0).attr("href");
			String productLinkPageSize = getWebPageSize(href);

			// Getting the description as well from the link of the title
			String productDescription = getDescriptionFromProductPage(href);

			// Building list of products
			Product product = new Product(productTitle, productLinkPageSize, productPricePerUnit, productDescription);
			productsList.add(product);
		}
		return productsList;
	}

	private static Document urlGet(String url) throws IOException {
		return Jsoup.connect(url).timeout(TIMEOUT).get();
	}

	private static String getDescriptionFromProductPage(String href) throws IOException {
		Document doc = urlGet(href);
		Element productDescription = doc.select("div.mainProductInfo").select("div.productText").select("p").first();
		return productDescription.text();
	}

	private static float extractPricePerUnit(TextNode textNode) {
		return Float.valueOf(df.format(Float.valueOf(textNode.text().replaceAll("&pound", ""))));
	}

	private static String getWebPageSize(String webUri) throws IOException {
		String kbSize = "";
		URLConnection conn = null;
		try {
			URL url = new URL(webUri);
			conn = url.openConnection();
			long size = conn.getContentLengthLong();
			if (size < 0)
				System.out.println("Could not determine file size.");
			else {
				kbSize = FileUtils.byteCountToDisplaySize(size);
				kbSize = kbSize.replaceAll(" ", "").toLowerCase();
			}
		} finally {
			try {
				conn.getInputStream().close();
			} catch (IOException e) {
				System.out.println("Could not close the input stream.");
				e.printStackTrace();
			}
		}
		return kbSize;
	}
}
