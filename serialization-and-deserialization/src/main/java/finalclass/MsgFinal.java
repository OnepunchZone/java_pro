package finalclass;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MsgFinal {
    private String chatIdentifier;
    private String lastMemberName;
    private String belongNumber;
    private String sendDate;
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MsgFinal msgFinal = (MsgFinal) o;
        return Objects.equals(belongNumber, msgFinal.belongNumber) && Objects.equals(sendDate, msgFinal.sendDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(belongNumber, sendDate);
    }

    @Override
    public String toString() {
        return "MsgFinal{" +
                "chatIdentifier='" + chatIdentifier + '\'' +
                ", lastMemberName='" + lastMemberName + '\'' +
                ", belongNumber='" + belongNumber + '\'' +
                ", sendDate='" + sendDate + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
