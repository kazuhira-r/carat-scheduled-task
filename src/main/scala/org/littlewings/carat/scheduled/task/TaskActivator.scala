package org.littlewings.carat.scheduled.task

import javax.enterprise.inject.spi.{Bean, CDI}
import javax.servlet.annotation.WebListener
import javax.servlet.{ServletContextEvent, ServletContextListener}

import org.apache.deltaspike.scheduler.spi.Scheduler
import org.quartz.Job
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.JavaConverters._

@WebListener
class TaskActivator extends ServletContextListener {
  private[task] val logger: Logger = LoggerFactory.getLogger(getClass)

  override def contextInitialized(sce: ServletContextEvent): Unit = {
    val scheduler = CDI.current.select(classOf[Scheduler[Job]]).get

    val beanManager = CDI.current.getBeanManager
    val jobBeans = beanManager.getBeans(classOf[Job]).asInstanceOf[java.util.Set[Bean[_ <: Job]]].asScala

    logger.info("found job-beans size = {}", jobBeans.size)

    jobBeans.foreach { jobBean =>
      logger.info("register job-class = {}", jobBean)
      scheduler.registerNewJob(jobBean.getBeanClass.asInstanceOf[Class[Job]])
    }
  }

  override def contextDestroyed(sce: ServletContextEvent): Unit = ()
}
