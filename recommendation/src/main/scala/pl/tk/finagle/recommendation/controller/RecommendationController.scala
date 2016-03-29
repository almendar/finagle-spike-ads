package pl.tk.finagle.recommendation.controller

import javax.inject.{Inject, Singleton}

import com.twitter.finagle.http.Request
import com.twitter.finagle.tracing.ClientTracingFilter.TracingFilter
import com.twitter.finatra.http.Controller
import com.twitter.finatra.request._
import com.twitter.inject.Logging
import pl.tk.finagle.recommendation.engine.{RecommendationCmd, RecommendationEngine}


case class RecommendationFromCookieRequest(@Inject request: Request,
                                           @RouteParam cookieId: Int,
                                           @RouteParam eshopId: Int,
                                           @Header `x-auth`: String)

@Singleton
class RecommendationController @Inject()(recommendationEngine: RecommendationEngine)
  extends Controller with Logging {

  get("/recommend/:eshop_id/:cookie_id") { r: RecommendationFromCookieRequest =>
    TracingFilter("RecommendationEngine") andThen
      recommendationEngine apply
      RecommendationCmd(r.cookieId, r.eshopId)
  }

}