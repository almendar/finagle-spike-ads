package pl.tk.finagle.recommendation.services.products

import javax.inject.Inject

import com.twitter.finagle.{Filter, Service}
import com.twitter.finagle.http.Method.Get
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.util.Future
import pl.tk.finagle.recommendation.{GetEshopProducts, Product}

private[products]
class GetPromotedProductFilter @Inject()(mapper: FinatraObjectMapper) extends Filter[String, List[Product], Request, Response] {

  override def apply(eshopId: String, service: Service[Request, Response]): Future[List[Product]] = {
    val req = Request(Get, s"/products/$eshopId")
    req.headerMap += "x-auth" -> "secretObtainedFromSomewhere"
    service(req).map { response =>
      mapper.parse[List[Product]](response.content)
    }
  }
}
