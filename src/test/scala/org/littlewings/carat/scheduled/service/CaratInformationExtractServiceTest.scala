package org.littlewings.carat.scheduled.service

import java.time.LocalDate
import javax.inject.Inject

import org.apache.deltaspike.testcontrol.api.mock.DynamicMockManager
import org.jsoup.Jsoup
import org.junit.Test
import org.littlewings.carat.scheduled.entity.Information
import org.littlewings.carat.scheduled.test.{CdiTestRunnerSupport, TestResourceLoader}
import org.mockito.Matchers._
import org.mockito.Mockito._

class CaratInformationExtractServiceTest extends CdiTestRunnerSupport {
  @Inject
  var mockManager: DynamicMockManager = _

  @Inject
  var caratInformationExtractService: CaratInformationExtractService = _

  @Test
  def extractInformations(): Unit = {
    val caratInformationExtractServiceSpy = spy(classOf[CaratInformationExtractService])
    doReturn(Jsoup.parse(TestResourceLoader.byClass(getClass, getClass.getSimpleName + "_index.html")))
      .when(caratInformationExtractServiceSpy)
      .loadDocument(any[String])

    mockManager.addMock(caratInformationExtractServiceSpy)

    val expected = Array(
      Information(LocalDate.of(2016, 8, 23), "http://www.carat-ue.jp/#live", "LIVE＆EVENT 更新"),
      Information(LocalDate.of(2016, 8, 18), "http://www.carat-ue.jp/#media", "MEDIA 更新"),
      Information(LocalDate.of(2016, 8, 8), "http://www.carat-ue.jp/profile.html", "AP 変更"),
      Information(LocalDate.of(2016, 8, 8), "http://www.carat-ue.jp/#new-release", "NEW RELEASE 更新")
    )

    caratInformationExtractService.extract should contain theSameElementsInOrderAs(expected)

    verify(caratInformationExtractServiceSpy).loadDocument(any[String])
  }
}
