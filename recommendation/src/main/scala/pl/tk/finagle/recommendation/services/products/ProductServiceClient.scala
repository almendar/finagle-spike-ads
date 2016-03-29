package pl.tk.finagle.recommendation.services.products

import javax.inject.Inject

import com.google.inject.ImplementedBy
import com.twitter.util.Future
import pl.tk.finagle.recommendation.{Product, GetEshopProducts}


@ImplementedBy(classOf[ProductServiceClientImpl])
trait ProductServiceClient {
  def getProducts(req: GetEshopProducts): Future[List[Product]]
  def getDefaultPromotedProducts(eshopId: String): Future[List[Product]]
}


private class ProductServiceClientImpl @Inject()(productsService: ProductsService,
                                                 getProductFilter: GetProductFilter,
                                                 getPromotedProductFilter: GetPromotedProductFilter) extends ProductServiceClient {
  override def getProducts(req: GetEshopProducts): Future[List[Product]] =
    (getProductFilter andThen productsService) (req)

  override def getDefaultPromotedProducts(eshopId: String): Future[List[Product]] =
    (getPromotedProductFilter andThen productsService) (eshopId)
}
