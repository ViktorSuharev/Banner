# Banner
API to add and get banners with weight priority

Banner has 2 params:
  - id
  - weight

API
- /banners/get (GET request with param: "count")
  gets count banners from the list according following strategy: the change of getting banner is in direct ratio of its weight param

- /banners (POST request with param: banner)
  adds banner to list. Id is generated automatically
