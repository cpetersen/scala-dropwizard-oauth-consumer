package io.petersen.oauth.consumer.db

import io.dropwizard.db.DataSourceFactory

import io.petersen.oauth.consumer.core.AccessToken
// import com.google.common.base.Optional
import io.dropwizard.hibernate.AbstractDAO
import org.hibernate.{ SessionFactory, ScrollableResults, ScrollMode }

import java.util.List;

class AccessTokenDAO(val sessionFactory: SessionFactory) extends AbstractDAO[AccessToken](sessionFactory) {

    def findById(id : Long) : AccessToken = get(id)
    def create(accessToken : AccessToken) : Long = persist(accessToken).getId()
    def findAll : List[AccessToken] = list(namedQuery("io.petersen.oauth.consumer.core.AccessToken.findAll"))
    def scrollAll : ScrollableResults = namedQuery("io.petersen.oauth.consumer.core.AccessToken.findAll").setReadOnly(true).setCacheable(false).scroll(ScrollMode.FORWARD_ONLY)
}
