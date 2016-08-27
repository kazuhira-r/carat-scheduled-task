package org.littlewings.carat.scheduled.config

import org.apache.deltaspike.core.api.config.PropertyFileConfig

class CaratTaskPropertyFileConfig extends PropertyFileConfig {
  override def getPropertyFileName: String = "carat-task.properties"

  override def isOptional: Boolean = false
}
