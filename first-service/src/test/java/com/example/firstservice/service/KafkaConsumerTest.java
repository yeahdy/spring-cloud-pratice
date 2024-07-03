package com.example.firstservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.firstservice.IntegrationTest;
import com.example.firstservice.messagequeue.KafkaConsumerService;
import com.example.firstservice.messagequeue.KafkaProducerService;
import org.junit.jupiter.api.Order;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

@Order(0)
@DirtiesContext
public class KafkaConsumerTest extends IntegrationTest {
  // infra 계층의 코드는 testcontainer를 통해 실제로 검증을 하고, KafkaConsumerService 는 서비스 계층으로 모킹을 통해 테스트
  @Autowired private KafkaProducerService kafkaProducerService;
  @MockBean private KafkaConsumerService kafkaConsumerService;

  //  @Test
  //  @DisplayName("Kafka 메세지 주고 받기")
  void kafka_send_and_consume_test() {
    String topic = "score-topic";
    String expectMessage = "Passed the final exam";

    // when
    kafkaProducerService.send(topic, expectMessage);

    // then
    var messageCaptor = ArgumentCaptor.forClass(String.class);
    Mockito.verify(kafkaConsumerService, Mockito.timeout(5000).times(1))
        .process(messageCaptor.capture());
    assertThat(messageCaptor.getValue()).isEqualTo(expectMessage);
  }
}
