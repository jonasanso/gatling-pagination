gatling-pagination
=============

Gatling load test example for a resource with pagination

To run: ```$ sbt gatling-it:test```

Reports are generated into the ```/target/gatling``` folder

Tweak the test parameters directly in [ServiceSimulation.scala](/src/test/scala/ServiceSimulation.scala)

Gatling configuration can be modified in [gatling.conf](/src/test/resources/gatling.conf)

## References:
* https://github.com/blinkboxbooks/gatling-sbt-template
