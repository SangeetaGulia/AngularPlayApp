import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

/**
  * Created by knodus on 15/3/16.
  */

@RunWith(classOf[JUnitRunner])
class InternControllerSpec extends Specification{

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beSome.which (status(_) == NOT_FOUND)
    }

    "render the index page : BadRequest" in new WithApplication{
      val home = route(FakeRequest(POST, "/addIntern").withFormUrlEncodedBody("name"->"","email"->"","mobile"->"","address"->"","emergencyContact"->"","id"->"")).get
      status(home) must equalTo(303)

    }

    "render the index page " in new WithApplication{
      val home = route(FakeRequest(POST, "/addIntern").withFormUrlEncodedBody("name"->"afsd","email"->"afd@hdg.com","mobile"->"423","address"->"sdgf","emergencyContact"->"35436","id"->"352")).get
      status(home) must equalTo(OK)

    }

    "get Interns " in new WithApplication{
      val home = route(FakeRequest(GET, "/myIntern")).get
      status(home) must equalTo(OK)

    }

    "render index " in new WithApplication{
      val home = route(FakeRequest(GET, "/intern")).get
      status(home) must equalTo(OK)

    }

    "render form " in new WithApplication{
      val home = route(FakeRequest(GET, "/form")).get
      status(home) must equalTo(OK)

    }

  }

}
