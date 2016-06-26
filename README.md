gatling-pagination
=============

Gatling load test example for a resource with pagination

In the directory data there are a few Json files with a common structure for pagination
```json
{
  "meta": {
    "next" : "https://raw.githubusercontent.com/jonasanso/gatling-pagination/master/data/second.json"
  },
  "data" : []
}
```

The load scenario will follow the next url until while it is present in the responses.

To run: ```$ sbt gatling-it:test```


## References:
* http://gatling.io/docs/2.2.2/
* https://github.com/blinkboxbooks/gatling-sbt-template
