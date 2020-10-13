package main.java.com.Lambda;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Bean {
    private Integer cardAmount;
    private Integer type;
    private Integer status;

    public Integer getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(Integer cardAmount) {
        this.cardAmount = cardAmount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "bean{" +
                "cardAmount=" + cardAmount +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
