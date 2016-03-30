import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.inject.app.TestInjector
import com.twitter.inject.{Injector, IntegrationTest}
import pl.tk.finagle.userprofile.{Desktop, Mobile, UserProfileJacksonModule}

class MarshallingTest extends IntegrationTest {
  override protected def injector: Injector = TestInjector(
    UserProfileJacksonModule
//      MessageBodyModule,
//    FinatraJacksonModule,
//    MustacheModule,
//    DocRootModule,
//    StatsReceiverModule
  )

  val mapper = injector.instance[FinatraObjectMapper]

  "Mobile marshall" in {
    assertResult("\"mobile\"")(mapper.writeValueAsString(Mobile))
  }

  "Desktop marshall" in {
    assertResult("\"desktop\"")(mapper.writeValueAsString(Desktop))
  }


}
