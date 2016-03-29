package pl.tk.finagle.recommendation
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.{CommonFilters, LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.finatra.http.routing.HttpRouter

class RecommendationHttpServer extends HttpServer {
  override def configureHttp(router: HttpRouter) {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[pl.tk.finagle.recommendation.controller.RecommendationController]
      .add[pl.tk.finagle.recommendation.controller.SwaggerController]
  }

  //@TODO
  override def warmup() {
//    run[TwitterCloneWarmup]()
  }
}


object Recommendation extends RecommendationHttpServer
