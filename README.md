gatling-pagination
=============

Gatling load test example for a resource with pagination

To run: ```$ sbt gatling-it:test```

A basic scenario will be run requesting from github the file in data/first.json
As first.json contains a next url inside meta, the next url will be executed and so on until a file without next url is reached.


Reports are generated into the ```/target/gatling``` folder

Tweak the test parameters directly in [ServiceSimulation.scala](/src/test/scala/ServiceSimulation.scala)

Gatling configuration can be modified in [gatling.conf](/src/test/resources/gatling.conf)

## References:
*
* https://github.com/blinkboxbooks/gatling-sbt-template
