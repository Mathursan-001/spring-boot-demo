package com.game.demo.service;

import com.game.demo.DTO.ScoreResponseDTO;
import com.game.demo.entity.AppUser;
import com.game.demo.entity.Score;
import com.game.demo.repository.GameRepository;
import com.game.demo.repository.UserRepository;
import com.game.demo.util.GameUtil;
import com.opencsv.CSVWriter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    private final UserRepository userRepository;

    @Transactional
    public ScoreResponseDTO getScoreResult(Integer value,Long userId){

        Integer generatedNum = GameUtil.getRandom();

        gameRepository.save(
                Score
                        .builder()
                        .user(userRepository.findById(userId).orElseThrow())
                        .generatedNumber(generatedNum)
                        .enteredNumber(value)
                        .attemptDateTime(new Date())
                        .build()

        );

        String result = GameUtil.getResult(value,generatedNum);

        AppUser user = userRepository.findById(userId).orElseThrow();

        if(result.equals("LOSE")){
            user.setLoses(user.getLoses()+1);
        }else{
            user.setWins(user.getWins()+1);
        }

        userRepository.save(user);

        return ScoreResponseDTO
                .builder()
                .result(result)
                .build();

    }

    public void writeStatFile(){

      File file = new File("stat.csv");

        try {
            FileWriter fileWriter = new FileWriter(file);
            CSVWriter csvWriter = new CSVWriter(fileWriter);

            List<Score> scores = gameRepository.findAll();

            String[] header = {"USER_NAME","ENTERED_NUMBER","GENERATED_NUMBER","DATE_TIME"};

            csvWriter.writeNext(header);

            scores.forEach((score)->{
                String[] record = {
                        score.getUser().getUserName(),
                        score.getEnteredNumber().toString(),
                        score.getGeneratedNumber().toString(),
                        score.getAttemptDateTime().toString()
                };

                csvWriter.writeNext(record);

            });

            csvWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



}
