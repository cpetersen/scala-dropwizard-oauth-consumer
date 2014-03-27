package io.petersen.oauth.consumer.core

import scala.reflect.BeanProperty
import javax.persistence.{ Entity, Table, NamedQueries, NamedQuery, Column, Id, GeneratedValue, GenerationType }
import com.fasterxml.jackson.annotation.{ JsonIgnore, JsonProperty }

@Entity
@Table(name = "access_tokens")
@NamedQueries(Array(
    new NamedQuery(
            name = "io.petersen.oauth.consumer.core.AccessToken.findAll", 
            query = "SELECT a FROM AccessToken a"
    ),
    new NamedQuery(
            name = "io.petersen.oauth.consumer.core.AccessToken.findById",
            query = "SELECT a FROM AccessToken a WHERE a.id = :id"
    ),
    new NamedQuery(
            name = "io.petersen.oauth.consumer.core.AccessToken.findByUid",
            query = "SELECT a FROM AccessToken a WHERE a.uid = :uid"
    )
))
class AccessToken( _accessToken : String , _uid : Long ){
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  @BeanProperty val id : Long = 0

  @Column(name = "access_token", nullable = false)
  @JsonProperty("access_token")
  @BeanProperty val accessToken : String = _accessToken

  @Column(name = "uid", nullable = false)
  @BeanProperty val uid : Long = _uid

  @Column(name = "cursor", nullable = true)
  @JsonIgnore
  @BeanProperty val cursor : String = null
}
