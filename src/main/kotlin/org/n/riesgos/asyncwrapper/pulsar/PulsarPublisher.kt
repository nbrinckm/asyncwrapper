package org.n.riesgos.asyncwrapper.pulsar

import org.apache.pulsar.client.api.CompressionType
import org.apache.pulsar.client.api.Producer
import org.apache.pulsar.client.api.TypedMessageBuilder
import org.springframework.stereotype.Component

@Component
class PulsarPublisher(var clientService: PulsarClientService) {

    val topic: String = "output-topic"
    val producer: Producer<ByteArray>

    init{
        producer = clientService.pulsarClient.newProducer()
            .topic(topic)
            .compressionType(CompressionType.LZ4)
            .create()
    }


    fun publishMessage(content: String){
        val msg: TypedMessageBuilder<ByteArray> = producer.newMessage();
        msg.value(content.toByteArray())
        //send message
        msg.send()
    }


}