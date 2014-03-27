package io.petersen.oauth.consumer.health

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result

class TemplateHealthCheck(val template: String) extends HealthCheck {
  @Override
  protected def check(): Result = {
    val saying: String = String.format(template, "TEST")
    if (!saying.contains("TEST")) {
      Result.unhealthy("template doesn't include a name");
    } else {
      Result.healthy();
    }
  }
}
