package br.edu.utfpr.bowlinggame.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shot")
public class Shot {

    protected Shot() { /* FOR SPRING */ }

    public Shot(Builder builder) {
        this.id = builder.id;
        this.pinsDropped = builder.pinsDropped;
        this.round = builder.round;
        this.roundShotNumber = builder.roundShotNumber;
    }

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "pins_dropped", nullable = false)
    private Integer pinsDropped;

    @Column(nullable = false)
    private Integer round;

    @Column(name = "round_shot_number", nullable = false)
    private Integer roundShotNumber;

    public UUID getId() {
        return id;
    }

    public Integer getPinsDropped() {
        return pinsDropped;
    }

    public Integer getRound() {
        return round;
    }

    public Integer getRoundShotNumber() {
        return roundShotNumber;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Shot)) return false;
        Shot that = (Shot) obj;
        return this.pinsDropped == that.pinsDropped
                && this.round == that.round
                && this.roundShotNumber == that.roundShotNumber;
    }

    @Override
    public String toString() {
        return Shot.class.getSimpleName() + "{"
                + "id=" + id + ","
                + "pinsDropped=" + pinsDropped + ","
                + "round=" + round + ","
                + "roundShotNumber=" + roundShotNumber
                + "}";
    }

    public static final class Builder {
        private UUID id;
        private Integer pinsDropped;
        private Integer round;
        private Integer roundShotNumber;

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withPinsDropped(Integer pinsDropped) {
            this.pinsDropped = pinsDropped;
            return this;
        }

        public Builder withRound(Integer round) {
            this.round = round;
            return this;
        }

        public Builder withRoundShotNumber(Integer roundShotNumber) {
            this.roundShotNumber = roundShotNumber;
            return this;
        }

        public Shot build() {
            return new Shot(this);
        }
    }
}
