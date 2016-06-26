package org.pagination

import io.gatling.core.Predef._
import io.gatling.core.session.Expression
import io.gatling.core.structure.ChainBuilder
import io.gatling.commons.validation._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class PaginationSimulation extends Simulation {

  private val STOP = "stop"
  private val httpConf = http.baseURL("https://raw.githubusercontent.com/jonasanso/gatling-pagination/master")

  def paginate = scenario("Pagination Simulation")
    .exec(
      paginatedRequest("/data/first.json", "first"))
    .asLongAs(condition = nextUrlFound, "page") {
      paginatedRequest("${next}", "next ${page} ")
    }


  private def paginatedRequest(url:Expression[String], name: Expression[String]): ChainBuilder = {
    exec(
      http(name)
        .get(url)
        .check(status.is(200))
        .check(
          jsonPath("$.meta.next")
            .transformOption(extract =>
              extract.orElse(Some(STOP)).success)
        .saveAs("next"))
    ).pause(2.seconds)
  }

  private def nextUrlFound(session: Session) =
    session("next").as[String] != STOP


  setUp(
    paginate.inject(rampUsersPerSec(1) to 3 during 10.seconds)
  ).protocols(httpConf)

}
