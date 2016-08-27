package org.littlewings.carat.scheduled.test

import java.io.InputStream

import scala.io.Source

object TestResourceLoader {
  def byClassLoader(clazz: Class[_], resourcePath: String): String = byClassLoader(clazz.getClassLoader, resourcePath)

  def byClassLoader(classLoader: ClassLoader, resourcePath: String): String = {
    val is = classLoader.getResourceAsStream(resourcePath)

    try {
      byInputStream(is)
    } finally {
      is.close()
    }
  }

  def byClass(clazz: Class[_], resourcePath: String): String = {
    val is = clazz.getResourceAsStream(resourcePath)

    try {
      byInputStream(is)
    } finally {
      is.close()
    }
  }

  private[test] def byInputStream(is: InputStream): String = {
    val source = Source.fromInputStream(is, "UTF-8")

    try {
      source.mkString
    } finally {
      source.close()
    }
  }
}
