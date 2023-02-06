import com.dimafeng.testcontainers.{Container, ForAllTestContainer, GenericContainer}
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}
import org.testcontainers.containers.Network
import org.testcontainers.utility.Base58
import com.dimafeng.testcontainers.{ForAllTestContainer, KafkaContainer}
import org.scalatest.flatspec.AnyFlatSpec

import java.util
import java.util.Properties

class Test extends AnyFlatSpec with ForAllTestContainer {
  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new util.Stack[Int]
    stack.push(1)
    stack.push(2)
    assert(stack.pop() === 2)
    assert(stack.pop() === 1)
  }

  val KafkaPort = 9093

  override val container: KafkaContainer = KafkaContainer()


  "Kafka container" should "be started" in {

    val properties = new Properties()
    properties.put("bootstrap.servers", container.bootstrapServers)
    properties.put("group.id", "consumer-tutorial")
    properties.put("key.deserializer", classOf[StringDeserializer])
    properties.put("value.deserializer", classOf[StringDeserializer])

    val kafkaConsumer = new KafkaConsumer[String, String](properties)
    val topics = kafkaConsumer.listTopics()

    assert(topics.size() >= 0)
  }







}
