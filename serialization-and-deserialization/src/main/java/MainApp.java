import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import deserialization.structure.ChatSession;
import deserialization.structure.ChatSessionList;
import finalclass.MsgFinal;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp {
    public static void main(String[] args) {
        ObjectMapper om = new ObjectMapper();

        try {
            ChatSessionList chatList = om.readValue(new File(
                    "C:\\Sazone\\JavaDev\\JavaPro\\serialization-and-deserialization\\sms-256866-434ec1.json"
            ), ChatSessionList.class);

            List<ChatSession> chatSessions = chatList.getChatSessions();

            List<MsgFinal> msgFinals = chatSessions.stream()
                    .flatMap(chat -> {
                        String chatId = chat.getChatIdentifier();
                        String lastMemberName = chat.getMembers().get(0).getLast();

                        return chat.getMessages().stream()
                                .map(msg -> new MsgFinal(
                                        chatId,
                                        lastMemberName,
                                        msg.getBelongNumber(),
                                        msg.getSendDate(),
                                        msg.getText()
                                ));
                    })
                    .distinct()
                    .sorted(Comparator.comparing(MsgFinal::getSendDate))
                    .collect(Collectors.toList());

            toJsonFunc(om, msgFinals);

            toXmlFunc(msgFinals);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void toJsonFunc(ObjectMapper om, List<MsgFinal> msgFinal) throws IOException {
        om.writeValue(new File("newJ.json"), msgFinal);
        List<MsgFinal> jsonResult = om.readValue(new File("newJ.json"), new TypeReference<>() {});
        System.out.println("JSON файл: " + jsonResult);
    }

    private static void toXmlFunc(List<MsgFinal> msgFinal) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File("newX.xml"), msgFinal);

        List<MsgFinal> xmlResult = xmlMapper.readValue(new File("newX.xml"), new TypeReference<>() {});
        System.out.println("XML файл: " + xmlResult);
    }
}
