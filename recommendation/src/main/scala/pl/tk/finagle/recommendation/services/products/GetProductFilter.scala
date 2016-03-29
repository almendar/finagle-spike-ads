package pl.tk.finagle.recommendation.services.products

import javax.inject.Inject

import com.twitter.finagle.http.Method.Get
import com.twitter.finagle.{Filter, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.httpclient.RequestBuilder
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.io.Buf.Utf8
import com.twitter.util.Future
import pl.tk.finagle.recommendation.{GetEshopProducts, Product}

private[products]
class GetProductFilter @Inject()(mapper: FinatraObjectMapper) extends Filter[GetEshopProducts, List[Product], Request, Response] {
  override def apply(request: GetEshopProducts, service: Service[Request, Response]): Future[List[Product]] = {

    val req = Request(Get,
      Request.queryString(s"/products/${request.eshopId}", "limit" -> request.limit.toString)
    )
    req.headerMap += "x-auth" -> "secretObtainedFromSomeWhere"
    service(req).map { response =>
      //no error handling
      mapper.parse[List[Product]](response.content)
    }
  }
}
