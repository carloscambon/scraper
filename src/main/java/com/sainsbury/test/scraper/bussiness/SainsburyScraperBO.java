package com.sainsbury.test.scraper.bussiness;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import com.sainsbury.test.scraper.beans.Product;
import com.sainsbury.test.scraper.utils.HtmlParser;

/**
 * Sainsbury-specific html selection and handling to fill the product list from
 * the url given
 * 
 * @author Carlos Cambon
 *
 */
public class SainsburyScraperBO {

	private NumberFormat df = NumberFormat.getInstance(Locale.UK);

	public SainsburyScraperBO() {
		df.setMinimumFractionDigits(2);
	}

	/**
	 * Returns a list of products scraped from the url given and manipulated as
	 * required
	 * 
	 * @param url
	 * @return List of products
	 */
	public List<Product> getProductList(String url) {
		Document doc;
		List<Product> productsList = new ArrayList<Product>();

		try {
			doc = HtmlParser.getDocumentFromUrl(url);

			Elements products = doc.select("div.product ");

			for (Element productElement : products) {

				// Getting the product title
				Elements title = getProductTitleElement(productElement);
				String productTitle = title.get(0).text();

				// Getting the product price per unit
				Elements price_unit1 = getPricePerUnitElement(productElement);
				float productPricePerUnit = extractPricePerUnit(price_unit1.get(0).textNodes().get(0));

				// Grabbing the link on the title to get the size of that page
				// (no assets)
				String href = title.get(0).attr("href");
				String productLinkPageSize = HtmlParser.getWebPageSize(href);

				// Getting the description as well from the link of the title
				String productDescription = getDescriptionFromProductPage(href);

				// Building list of products
				Product product = new Product(productTitle, productLinkPageSize, productPricePerUnit,
						productDescription);
				productsList.add(product);
			}
		} catch (IOException e) {
			System.out.println("[SainsburyScraperBO] Could not Scrape the site. Please verify the url given.\n\n");
			e.printStackTrace();
		}
		return productsList;
	}

	private String getDescriptionFromProductPage(String href) throws IOException {
		Document doc = HtmlParser.getDocumentFromUrl(href);
		Element productDescription = doc.select("div.mainProductInfo").select("div.productText").select("p").first();
		return productDescription.text();
	}

	private float extractPricePerUnit(TextNode textNode) {
		return Float.valueOf(df.format(Float.valueOf(textNode.text().replaceAll("&pound", ""))));
	}

	private Elements getProductTitleElement(Element productElement) {
		return productElement.select("div.productInfo").select("h3").select("a");
	}

	private Elements getPricePerUnitElement(Element productElement) {
		return productElement.select("div.addToTrolleytabBox").select("div.pricing").select("p.pricePerUnit");
	}

}
