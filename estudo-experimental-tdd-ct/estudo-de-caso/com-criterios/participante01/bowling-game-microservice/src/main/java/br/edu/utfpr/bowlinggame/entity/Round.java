package br.edu.utfpr.bowlinggame.entity;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.utfpr.bowlinggame.exception.InvalidShotValueException;

@Entity
@Table(name = "round")
public class Round {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private Integer shotOne;
	private Integer shotTwo;
	private Integer score;
	
	protected Round(){
	}
	
	public Round(RoundBuilder roundBuilder) {
		this.id = roundBuilder.id;
		this.score = roundBuilder.score;
		this.shotOne = roundBuilder.shotOne;
		this.shotTwo = roundBuilder.shotTwo;
	}
	
	public Round(Integer shotOne){
		this.shotOne = shotOne;
		this.score = shotOne;
	}

	public Integer getScore() {
		return score;
	}
	
	public UUID getId() {
		return id;
	}
	
	public Integer getShotOne() {
		return shotOne;
	}
	
	public Integer getShotTwo() {
		return shotTwo;
	}
	
	public static RoundBuilder builder(){
		return new RoundBuilder();
	}
	
	public static final class RoundBuilder {
		private UUID id;
		private Integer score;
		private Integer shotOne;
		private Integer shotTwo;
	

		private RoundBuilder(){
		}
		
		public RoundBuilder withId(UUID id){
			this.id = id;
			return this;
		}
	
		public RoundBuilder withScore(Integer score){
			this.score = score;
			return this;
		}
		
		public RoundBuilder withShotOne(Integer shotOne){
			this.shotOne = shotOne;
			return this;
		}
		
		public RoundBuilder withShotTwo(Integer shotTwo){
			this.shotTwo = shotTwo;
			return this;
		}
		
		public Round build() {
			return new Round(this);
		}
	}

	public boolean isComplete() {
		return shotOne == 10 || (shotOne != null && shotTwo != null);
	}
	
	public void addShotTwo(Integer shot) {
		if(this.score + shot > 10){
			throw new InvalidShotValueException();
		}
		this.shotTwo = shot;
		this.score = this.score + shot;		
	}
	

	 @Override
    public int hashCode() {
        return Objects.hash(shotOne, id, score, shotTwo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Round)) {
            return false;
        }
        Round other = (Round) obj;
        return Objects.equals(shotOne, other.shotOne) && Objects.equals(id, other.id)
                && Objects.equals(score, other.score) && Objects.equals(shotTwo, other.shotTwo);
    }
	
}
