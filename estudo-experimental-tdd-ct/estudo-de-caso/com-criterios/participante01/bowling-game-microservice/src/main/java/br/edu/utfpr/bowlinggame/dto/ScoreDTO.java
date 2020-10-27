package br.edu.utfpr.bowlinggame.dto;

public class ScoreDTO {
	
	private Integer score;
	
	public ScoreDTO(Integer score) {
		this.score = score;
	}

	public Integer getScore() {
		return score;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreDTO other = (ScoreDTO) obj;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		return true;
	}
	
	
	
}
