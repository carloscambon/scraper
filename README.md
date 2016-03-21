## Synopsis

Project to scrape a Sainsbury’s grocery site - Ripe Fruits page and returns a JSON array of all the products on the page.

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

## Running the console application

Create a release as above and run the jar generated in "target" folder as:

```
java -jar scraper-[version].jar urlToScrape
```

Example:

```
java -jar scraper-1.0.jar http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html
```

Result of the execution:

```
Fetching http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html...{
  "results" : [ {
    "title" : "Sainsbury's Apricot Ripe & Ready x5",
    "size" : "38kb",
    "unit_price" : 3.5,
    "description" : "Apricots"
  }, {
    "title" : "Sainsbury's Avocado Ripe & Ready XL Loose 300g",
    "size" : "38kb",
    "unit_price" : 1.5,
    "description" : "Avocados"
  }, {
    "title" : "Sainsbury's Avocado, Ripe & Ready x2",
    "size" : "43kb",
    "unit_price" : 1.8,
    "description" : "Avocados"
  }, {
    "title" : "Sainsbury's Avocados, Ripe & Ready x4",
    "size" : "38kb",
    "unit_price" : 3.2,
    "description" : "Avocados"
  }, {
    "title" : "Sainsbury's Conference Pears, Ripe & Ready x4 (minimum)",
    "size" : "38kb",
    "unit_price" : 1.5,
    "description" : "Conference"
  }, {
    "title" : "Sainsbury's Golden Kiwi x4",
    "size" : "38kb",
    "unit_price" : 1.8,
    "description" : "Gold Kiwi"
  }, {
    "title" : "Sainsbury's Kiwi Fruit, Ripe & Ready x4",
    "size" : "38kb",
    "unit_price" : 1.8,
    "description" : "Kiwi"
  } ],
  "total" : 15.1
}
```
