package org.littlewings.carat.scheduled.task

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

import org.apache.deltaspike.scheduler.api.Scheduled
import org.littlewings.carat.scheduled.service.{CaratInformationExtractService, InformationUpdatedSearchService, TweetService}
import org.quartz.{Job, JobExecutionContext}
import org.slf4j.{Logger, LoggerFactory}

@Scheduled(cronExpression = "{carat.information.monitoring.schedule}", onStartup = false)
@ApplicationScoped
class CaratInformationMonitoringTask extends Job {
  private[task] val logger: Logger = LoggerFactory.getLogger(getClass)

  @Inject
  private[task] var caratInformationExtractService: CaratInformationExtractService = _

  @Inject
  private[task] var informationUpdatedSearchService: InformationUpdatedSearchService = _

  @Inject
  private[task] var tweetService: TweetService = _

  override def execute(context: JobExecutionContext): Unit = {
    logger.info("start task")

    val informations = caratInformationExtractService.extract
    logger.info("extract informations = {}", informations.size)

    val newInformations = informationUpdatedSearchService.findUpdated(informations)
    logger.info("new arrival informations = {}", newInformations.size)

    newInformations.foreach { newInformation =>
      tweetService.tweet(newInformation)
    }

    logger.info("end task")
  }
}
