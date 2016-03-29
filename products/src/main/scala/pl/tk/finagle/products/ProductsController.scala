package pl.tk.finagle.products

import javax.inject.Inject

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.finatra.request.{Header, QueryParam, RouteParam}
import com.twitter.inject.Logging

import scala.util.Random

case class Product(id: Int, name: String, price: BigDecimal, imageUrl: String, productDeepLink: String)

object Product {

  def newProduct: Product = Product(
    Random.nextInt(100),
    List.fill(Random.nextInt(7))(Random.nextPrintableChar()).mkString,
    BigDecimal(Random.nextDouble() * 500).setScale(2, BigDecimal.RoundingMode.HALF_UP),
    "http://someurl_to_image",
    "http://some_url_to_eshop"
  )

  val promoted: Map[Int,List[Product]] = (1 to 100).map(_ -> List.fill(10)(newProduct)).toMap
  val mockedProducts: Map[Int,Stream[Product]] = (1 to 100).map(_ ->Stream.continually(newProduct)).toMap
}

case class ProductsRequest(@Inject request: Request,
                           @RouteParam eshopId: Int,
                           @QueryParam limit: Int = 100,
                           @Header `x-auth`: String)

class ProductsController @Inject()(objectMapper: FinatraObjectMapper) extends Controller with Logging {

  get("/products/:eshop_id") { pr: ProductsRequest =>
    Product.mockedProducts.get(pr.eshopId).map(_.take(pr.limit).toList)
  }

  get("/products/:eshop_id/promoted") { pr: ProductsRequest =>
    Product.promoted.get(pr.eshopId)
  }
}
