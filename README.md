## Synopsis

Project to scrape a Sainsbury’s grocery site - Ripe Fruits page and returns a JSON array of all the products on the page.

## Quick start

You can download the latest version here [link] and use it from the command line:

```
java -jar scraper-[version].jar urlToScrape
```

Example:

```
java -jar scraper-1.0.jar http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html
```

## Installation on your IDE

Clone the repository:

https://github.com/carloscambon/scraper.git

and import the project on your IDE

## Tests

To run the tests simply execute the goal test on your project root, i.e.:

```
mvn test
```

## Release

To release a new version execute the following maven command:

```
mvn clean compile assembly:single
```

