package org.littlewings.carat.scheduled.service

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.littlewings.carat.scheduled.config.CaratTaskConfig
import org.littlewings.carat.scheduled.entity.Information

import scala.collection.JavaConverters._

@ApplicationScoped
class CaratInformationExtractService {
  @Inject
  private[service] var caratTaskConfig: CaratTaskConfig = _

  def extract: Seq[Information] = {
    val siteUrl = caratTaskConfig.getCaratSiteUrl

    val document = loadDocument(siteUrl)

    val updateDates = document.select("#information dl dt span.f11")
    val updateContents = document.select("#information dl dd a")

    updateDates
      .asScala
      .map(span => span.text)
      .zip(updateContents.asScala.map(a => (a.attr("href"), a.text)))
      .map { case (date, (href, content)) =>
        val absoluteLink =
          if (href.startsWith("http"))
            href
          else if (href.startsWith("/"))
            siteUrl + href.substring(1, href.length)
          else
            siteUrl + href

        val splittedDate = date.split("\\.")
        val (year, month, day) = (splittedDate(0), splittedDate(1), splittedDate(2))

        val revisedDate = year + "." + (if (month.size < 2) "0" + month else month) + "." + (if (day.size < 2) "0" + day else day)

        Information(LocalDate.parse(revisedDate, DateTimeFormatter.ofPattern("yyyy.MM.dd")), absoluteLink, content)
      }.toVector
  }

  private[service] def loadDocument(url: String): Document = Jsoup.connect(url).get
}
