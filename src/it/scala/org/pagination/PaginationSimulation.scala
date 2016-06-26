package org.pagination

import io.gatling.core.Predef._
import io.gatling.core.session.Expression
import io.gatling.core.structure.ChainBuilder
import io.gatling.core.validation._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class PaginationSimulation extends Simulation {

  val baseUrl = "https://raw.githubusercontent.com/jonasanso/gatling-pagination/master"
  val minUsersPerSecond = 1
  val maxUsersPerSecond = 5
  val testDuration = 5.minute

  val httpConf = http
    .baseURL(baseUrl)


  def paginate = scenario("Pagination Simulation")
    exec(
      paginatedRequest("/data/first.json"))
    .asLongAs(condition = session => session("next").as[String] != "end", "page", exitASAP = true) {
      paginatedRequest("${next}")
    }


  private def paginatedRequest(url:Expression[String]): ChainBuilder = {
    exec(
      http("paginate")
        .get(url)
        .check(status.is(200))
        .check(jsonPath("$..meta.next").transformOption(extract => extract.orElse(Some("end")).success)
          .saveAs("next"))
    )
  }

  setUp(
    paginate.inject(rampUsersPerSec(minUsersPerSecond) to maxUsersPerSecond during testDuration)
  ).protocols(httpConf)

}
