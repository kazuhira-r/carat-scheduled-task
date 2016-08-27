package org.littlewings.carat.scheduled.service

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

import org.littlewings.carat.scheduled.entity.Information
import org.slf4j.{Logger, LoggerFactory}
import twitter4j.Twitter

@ApplicationScoped
class TweetService {
  private[service] val logger: Logger = LoggerFactory.getLogger(getClass)

  @Inject
  private[service] var twitter: Twitter = _

  @Inject
  private[service] var informationFormatService: InformationFormatService = _

  def tweet(information: Information): Unit = {
    val formatted = informationFormatService.format(information)

    logger.info("tweet = {}", formatted)
    twitter.updateStatus(formatted)
  }
}
