package org.littlewings.carat.scheduled.config

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

import org.apache.deltaspike.core.api.config.ConfigProperty

@ApplicationScoped
class CaratTaskConfig {
  @Inject
  @ConfigProperty(name = "carat.site.url")
  private[config] var caratSiteUrl: String = _

  def getCaratSiteUrl: String = caratSiteUrl
}
