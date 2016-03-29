package pl.tk.finagle.recommendation.engine

import javax.inject.{Inject, Singleton}

import com.twitter.finagle.Service
import com.twitter.util.Future
import pl.tk.finagle.recommendation.{GetEshopProducts, Product}
import pl.tk.finagle.recommendation.services.products.ProductServiceClient
import pl.tk.finagle.recommendation.services.userprofiles.{UserProfile, UsersProfileClient}


case class RecommendationCmd(cookieId: Int, eshop: Int)

@Singleton
class RecommendationEngine @Inject()(productServiceClient: ProductServiceClient,
                                     usersProfileClient: UsersProfileClient) extends Service[RecommendationCmd, List[Int]] {
  override def apply(request: RecommendationCmd): Future[List[Int]] = {

    for {
      products <- productServiceClient.getProducts(GetEshopProducts(request.eshop.toString, 100))
      promotedProd <- productServiceClient.getDefaultPromotedProducts(request.eshop.toString)
      userProfile <- usersProfileClient.getProfile(request.cookieId)
    } yield {
      (promotedProd.map(_.id) ++ products.map(_.id)).zipWithIndex.collect {
        case (id: Int, index: Int) if index % userProfile.locations.size == index % userProfile.language.size => index
      }
    }
  }
}
