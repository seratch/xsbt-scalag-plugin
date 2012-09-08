import freemarker.cache._
import freemarker.template._

/**
 * Scalag common values and operations
 */
package object scalag {

  /**
   * Scalag command operation type
   */
  type ScalagOperation = PartialFunction[ScalagInput, Unit]

  /**
   * Singleton FreeMarker configuration value
   */
  val freeMarkerConfig: Configuration = {

    val config = new Configuration {
      private[this] var _loaders: Seq[TemplateLoader] = Nil

      def addLoader(loader: TemplateLoader) = {
        _loaders ++= Seq(loader)
        setTemplateLoader(new MultiTemplateLoader(_loaders.toArray))
        this
      }

      def setLoaders(ldrs: TemplateLoader*): this.type = {
        _loaders = ldrs.toList
        setTemplateLoader(new MultiTemplateLoader(_loaders.toArray))
        this
      }
    }

    config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER)
    config.setDefaultEncoding("UTF-8")
    config.setObjectWrapper(new ScalaObjectWrapper())
    config.addLoader(new ClassTemplateLoader(getClass, "/"))
    config
  }

  /**
   * Singleton FreeMarker instance
   */
  val freeMarker: FreeMarker = FreeMarker(freeMarkerConfig)

  /**
   * Returns string value using FreeMarker template
   * @param path ftl path
   * @param values binding values
   * @return string value
   */
  def ftl2string(path: String, values: Map[String, Any]): String = {
    freeMarker.ftl2string(path, values)
  }

}
