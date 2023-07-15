package producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * kafka 生产者
 */
public class CustomProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "20.5.2.69:9092");

        //key.serializer  value.serializer 必须要配置
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //创建生产者
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        kafkaProducer.send(new ProducerRecord<>("hiacloud-event", "{\n" +
                "    \"property\": {\n" +
                "        \"current\": true,\n" +
                "        \"subject\": \"event\",\n" +
                "        \"session\": \"jdauxpro\",\n" +
                "        \"exclusive\": true,\n" +
                "        \"sequenceId\": \"0\",\n" +
                "        \"version\": \"1.0\",\n" +
                "        \"target\": \"alarm\"\n" +
                "    },\n" +
                "    \"message\": {\n" +
                "        \"id\": \"159fdd68-77f9-4138-86c8-67dbe8ba8002\",\n" +
                "        \"data\": {\n" +
                "            \"current_status\": {\n" +
                "                \"status\": \"ONLINE\",\n" +
                "                \"time\": 1687221524891\n" +
                "            },\n" +
                "            \"device_id\": \"06fb20a698ed29130c596d6fd9b47eb1_f2164f809b874960a641f40ff3026149\",\n" +
                "            \"device_type\": \"网关设备\",\n" +
                "            \"last_status\": {\n" +
                "                \"status\": \"OFFLINE\",\n" +
                "                \"time\": 1687221501003\n" +
                "            },\n" +
                "            \"onlineMessage\": true\n" +
                "        },\n" +
                "        \"AlarmId\": \"159fdd68-77f9-4138-86c8-67dbe8ba8002\",\n" +
                "        \"EventId\": \"1\",\n" +
                "        \"EventType\": \"设备状态变更预警\",\n" +
                "        \"Namespace\": \"0000\",\n" +
                "        \"Severity\": 100,\n" +
                "        \"SourceId\": \"\",\n" +
                "        \"AssetId\": \"\",\n" +
                "        \"Retain\": true,\n" +
                "        \"ConditionId\": \"\",\n" +
                "        \"ConditionClassName\": \"离线告警\",\n" +
                "        \"ConditionClassId\": \"iotda_alarm\",\n" +
                "        \"Time\": 1687167404922\n" +
                "    }\n" +
                "}"));

    }
}
