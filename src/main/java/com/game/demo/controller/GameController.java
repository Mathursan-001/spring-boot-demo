package com.game.demo.controller;

import com.game.demo.DTO.ScoreResponseDTO;
import com.game.demo.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/score")
    public ResponseEntity<ScoreResponseDTO> getGameResult(@RequestParam
                                                              Integer num,
                                                          @RequestParam
                                                          Long id){
        //validation
        return ResponseEntity.ok(gameService.getScoreResult(num,id));

    }

    @GetMapping("/stats")
    public ResponseEntity<String> getStats(){
        gameService.writeStatFile();
        return ResponseEntity.accepted().body("Stat file generation begin");
    }





}
