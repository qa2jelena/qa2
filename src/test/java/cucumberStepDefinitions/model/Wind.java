package cucumberStepDefinitions.model;

import java.math.BigDecimal;

public class Wind {

    private BigDecimal speed;
    private Integer deg;

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }
}
