package org.littlewings.carat.scheduled.producer

import javax.enterprise.context.{ApplicationScoped, Dependent}
import javax.enterprise.inject.Produces

import twitter4j.{Twitter, TwitterFactory}

@Dependent
class TwitterProducer {
  @ApplicationScoped
  @Produces
  def twitter: Twitter = TwitterFactory.getSingleton
}
