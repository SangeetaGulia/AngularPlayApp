import org.specs2.mutable.Specification
import play.api.Application
import play.api.test.WithApplication
import repository.{Intern, InternRepo}
import scala.concurrent.duration._
import scala.concurrent.Await


/**
  * Created by akshay on 11/3/16.
  */
class InternRepoSpec extends Specification{


  "Intern repository" should {
    def internRepo(implicit app: Application) = Application.instanceCache[InternRepo].apply(app)

    "Get all interns records test" in new WithApplication {
      val result = Await.result(internRepo.getIntern, 5 seconds)
      assert(result.length === 3)

    }

    "Add intern test" in new WithApplication {
      val result = Await.result(internRepo.addIntern(Intern("akshay","aks@gmail.com",3256,"dabhri",354536)), 5 seconds)
      assert(result === 1)
    }
  }
}