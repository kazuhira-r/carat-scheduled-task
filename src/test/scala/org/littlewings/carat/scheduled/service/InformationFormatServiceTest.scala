package org.littlewings.carat.scheduled.service

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner
import org.junit.Test
import org.junit.runner.RunWith
import org.littlewings.carat.scheduled.entity.Information
import org.littlewings.carat.scheduled.test.{CdiTestRunnerSupport, ScalaTestJUnitTestSupport}

class InformationFormatServiceTest extends CdiTestRunnerSupport {
  @Inject
  var informationFormatService: InformationFormatService = _

  @Test
  def informationFormatTest(): Unit = {
    val updateDate = LocalDate.parse("2016.08.23", DateTimeFormatter.ofPattern("yyyy.MM.dd"))
    val linkUrl = "http://localhost/index.html"
    val content = "テストコンテンツが更新されました。"

    val information = Information(updateDate, linkUrl, content)

    informationFormatService.format(information) should be(
      s"""|[2016.08.23 オフィシャルサイト更新]
          |テストコンテンツが更新されました。
          |
         |http://localhost/index.html""".stripMargin)
  }
}
