package org.littlewings.carat.scheduled.repository

import javax.enterprise.context.ApplicationScoped

import org.littlewings.carat.scheduled.entity.Information

@ApplicationScoped
class InformationRepository {
  private[repository] var store: Vector[Information] = Vector.empty

  def saveAll(informations: Seq[Information]): Unit = synchronized {
    store = Vector(informations: _*)
  }

  def findAll: Seq[Information] = synchronized {
    Vector(store: _*)
  }

  def clear(): Unit = synchronized {
    store = Vector.empty
  }
}
