package pl.tk.finagle.recommendation

case class GetEshopProducts(eshopId:String, limit: Int)

case class Product(id: Int, name: String, price: BigDecimal)
