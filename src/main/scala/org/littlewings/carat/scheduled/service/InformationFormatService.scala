package org.littlewings.carat.scheduled.service

import java.time.format.DateTimeFormatter
import javax.enterprise.context.ApplicationScoped

import org.littlewings.carat.scheduled.entity.Information

@ApplicationScoped
class InformationFormatService {
  def format(information: Information): String =
    s"""|[${information.updateDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))} オフィシャルサイト更新]
        |${information.updateContent}
        |
       |${information.linkUrl}""".stripMargin
}
