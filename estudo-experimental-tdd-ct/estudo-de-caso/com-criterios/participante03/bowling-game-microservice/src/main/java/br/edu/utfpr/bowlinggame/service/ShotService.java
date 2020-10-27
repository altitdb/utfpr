package br.edu.utfpr.bowlinggame.service;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.edu.utfpr.bowlinggame.dto.RoundDTO;
import br.edu.utfpr.bowlinggame.entity.Shot;
import br.edu.utfpr.bowlinggame.exception.MaximumRoundsExceededException;
import br.edu.utfpr.bowlinggame.repository.ShotRepository;

@Service
@ComponentScan("br.edu.utfpr.bowlinggame.repository")
public class ShotService {

    private static final Integer MAX_ROUNDS = 10;
    private static final Integer MAX_ROUND_SHOTS = 2;

    @Autowired
    private ShotRepository shotRepository;

    public List<RoundDTO> doShot(int pinsDropped) {
        List<Shot> previousShots = shotRepository.findAll(Sort.by("round", "roundShotNumber"));

        Shot shot = buildShot(pinsDropped, previousShots);
        if (shot.getRound() > MAX_ROUNDS) {
            String message = String.format("Exceeded the maximum of %d rounds", MAX_ROUNDS);
            throw new MaximumRoundsExceededException(message);
        }
        shotRepository.save(shot);

        previousShots.add(shot);
        List<Shot> allShots = previousShots;
        return allShots.stream()
            .collect(Collectors.groupingBy(Shot::getRound))
            .entrySet()
            .stream()
            .map(Entry::getValue)
            .map(this::toRoundDTO)
            .collect(Collectors.toList());
    }

    private Shot buildShot(int pinsDropped, List<Shot> previousShots) {
        if (previousShots.isEmpty()) {
            return buildFirstShot(pinsDropped);
        }
        Shot lastPreviousShot = previousShots.get(previousShots.size() - 1);
        if (lastPreviousShot.getRoundShotNumber() < MAX_ROUND_SHOTS) {
            return Shot.newBuilder()
                    .withPinsDropped(pinsDropped)
                    .withRound(lastPreviousShot.getRound())
                    .withRoundShotNumber(lastPreviousShot.getRoundShotNumber() + 1)
                    .build();
        }
        return Shot.newBuilder()
                .withPinsDropped(pinsDropped)
                .withRound(lastPreviousShot.getRound() + 1)
                .withRoundShotNumber(1)
                .build();
    }

    private Shot buildFirstShot(int pinsDropped) {
        return Shot.newBuilder()
                .withPinsDropped(pinsDropped)
                .withRound(1)
                .withRoundShotNumber(1)
                .build();
    }

    private RoundDTO toRoundDTO(List<Shot> roundShots) {
        Shot firstShot = roundShots.get(0);
        if (roundShots.size() == 1) {
            return new RoundDTO(firstShot.getPinsDropped(), 0, firstShot.getPinsDropped());
        }
        Shot secondShot = roundShots.get(1);
        return new RoundDTO(
                firstShot.getPinsDropped(),
                secondShot.getPinsDropped(),
                firstShot.getPinsDropped() + secondShot.getPinsDropped()
        );
    }
}
