package pl.tk.finagle.recommendation

import com.google.inject.testing.fieldbinder.Bind
import com.twitter.finagle.http.Status
import com.twitter.finatra.http.test.{EmbeddedHttpServer, HttpTest}
import com.twitter.inject.Mockito
import com.twitter.inject.server.FeatureTest
import pl.tk.finagle.recommendation.engine.{RecommendationCmd, RecommendationEngine, RecommendedProducts}
import com.twitter.util.Future

class RecommendationControllerTest extends FeatureTest with Mockito with HttpTest {
  override val server = new EmbeddedHttpServer(new RecommendationHttpServer)

  @Bind val recommendationEngine = smartMock[RecommendationEngine]

  "RecommendationEngine" should  {
    "return list of recommended products ids" in {
      recommendationEngine.apply(RecommendationCmd(654,321)) returns Future.value(RecommendedProducts(List(4,22,4,5,6)))
      server.httpGet(
        path = "/recommend/321/654",
        headers = Map("x-auth" -> "222"),
        andExpect = Status.Ok,
        withJsonBody =
          s"""
             |{
             |  "recommendation" :  [4,22,4,5,6]
             |}
           """.stripMargin
      )
    }
  }

}
