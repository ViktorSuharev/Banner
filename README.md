# Banner
API to add and get banners with weight priority

Banner has 2 params:
  - id
  - weight

API
- /banners (GET request with param: "count")
  gets count banners from the list according following strategy: the change of getting banner is in direct ratio of its weight param

- /banners (POST request with request param: weight)
  adds banner to list. Id is generated automatically

- /banners/{id} (DELETE request with path variable: id)
  deletes banner by id
