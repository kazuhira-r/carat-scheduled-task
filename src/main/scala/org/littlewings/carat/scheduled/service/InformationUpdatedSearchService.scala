package org.littlewings.carat.scheduled.service

import java.time.{Clock, LocalDate, ZoneId}
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

import org.littlewings.carat.scheduled.entity.Information
import org.littlewings.carat.scheduled.repository.InformationRepository
import org.slf4j.{Logger, LoggerFactory}

@ApplicationScoped
class InformationUpdatedSearchService {
  private[service] val logger: Logger = LoggerFactory.getLogger(getClass)

  @Inject
  private[service] var informationRepository: InformationRepository = _

  def findUpdated(targets: Seq[Information]): Seq[Information] = {
    val currentInformations = informationRepository.findAll

    val notContains = targets.filterNot(target => currentInformations.contains(target))

    if (notContains.isEmpty) {
      Vector.empty
    } else {
      val today = LocalDate.now(clock)
      val yesterday = today.plusDays(-1)

      logger.info("today = {}, yesterday = {}", Array(today, yesterday): _*)

      informationRepository.saveAll(targets)

      notContains.filter(target => target.updateDate.isAfter(yesterday) || target.updateDate == yesterday)
    }
  }

  private[service] def clock: Clock = Clock.system(ZoneId.of("Asia/Tokyo"))
}
