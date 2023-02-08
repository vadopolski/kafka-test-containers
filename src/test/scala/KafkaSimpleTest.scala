import com.dimafeng.testcontainers.{Container, ForAllTestContainer, GenericContainer}
import org.apache.kafka.clients.consumer.{ConsumerRecord, KafkaConsumer}
import org.apache.kafka.common.serialization.{IntegerSerializer, StringDeserializer, StringSerializer}
import org.testcontainers.containers.Network
import org.testcontainers.utility.Base58
import com.dimafeng.testcontainers.{ForAllTestContainer, KafkaContainer}
import org.apache.kafka.clients.producer.ProducerConfig._
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.scalatest.flatspec.AnyFlatSpec

import java.time.Duration
import java.util
import java.util.Properties
import scala.collection.JavaConverters.{asScalaIteratorConverter, seqAsJavaListConverter}

class KafkaSimpleTest extends AnyFlatSpec with ForAllTestContainer {
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
    val producersProps = new Properties()
    producersProps.put(BOOTSTRAP_SERVERS_CONFIG, container.bootstrapServers)
    producersProps.put(CLIENT_ID_CONFIG, "test-containers")
    producersProps.put(KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
    producersProps.put(VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
    val kafkaProducer = new KafkaProducer[String, String](producersProps)

    val consumerProps = new Properties()
    consumerProps.put("bootstrap.servers", container.bootstrapServers)
    consumerProps.put("group.id", "consumer-tutorial")
    consumerProps.put("key.deserializer", classOf[StringDeserializer])
    consumerProps.put("value.deserializer", classOf[StringDeserializer])

    val kafkaConsumer = new KafkaConsumer[String, String](consumerProps)
    val topics = kafkaConsumer.listTopics()

    //    kafkaConsumer.subscribe(List(""))


    try {
      val testTopic = "topic1"
      val record = new ProducerRecord[String, String](testTopic, "100")
      val meta = kafkaProducer.send(record).get()

      println(s"Message written with offset = ${meta.offset()} and partition = ${meta.partition()}")

      kafkaConsumer.subscribe(List(testTopic).asJava)

      val records = kafkaConsumer.poll(Duration.ofSeconds(2))

      val result = records.iterator().asScala.toVector

      println(result)

    } finally {
      kafkaConsumer.close()
      kafkaProducer.close()
    }
  }


}
