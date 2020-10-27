package br.edu.utfpr.bowlinggame.dto;

public class RoundDTO {

    private int firstShot;
    private int secondShot;
    private int score;

    public RoundDTO() { }

    public RoundDTO(int firstShot, int secondShot, int score) {
        this.firstShot = firstShot;
        this.secondShot = secondShot;
        this.score = score;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RoundDTO)) return false;
        RoundDTO that = (RoundDTO) obj;
        return this.firstShot == that.firstShot && this.secondShot == that.secondShot && this.score == that.score;
    }

    @Override
    public String toString() {
        return RoundDTO.class.getSimpleName() + "{"
                + "firstShot=" + firstShot + ","
                + "secondShot=" + secondShot + ","
                + "score=" + score
                + "}";
    }
}
