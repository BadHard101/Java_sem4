package com.example.jft_pr16.service;

import com.example.jft_pr16.entity.Game;
import com.example.jft_pr16.entity.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
@ManagedResource(description = "Scheduler service bean")
public class SchedulerService {
    private final String BACKUP_DIR = "src/main/resources/backup";
    private LevelService levelService;
    private GameService gameService;

    @Autowired
    public SchedulerService(LevelService levelService, GameService gameServiceService) {
        this.levelService = levelService;
        this.gameService = gameServiceService;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    @ManagedOperation(description = "Clear output, create .txt with database data")
    public void backupDataBase() throws IOException {
        File dir = ResourceUtils.getFile(BACKUP_DIR);
        try {
            for (File file : dir.listFiles()){
                if (file.isFile()) {
                    file.delete();
                    System.out.println(file.getName() + " is deleted");
                }
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        BufferedWriter games = new BufferedWriter(
                new FileWriter(BACKUP_DIR + "/games.txt")
        );
        BufferedWriter level = new BufferedWriter(
                new FileWriter(BACKUP_DIR + "/levels.txt")
        );

        for (Game item : gameService.getGames()){
            games.write(item.toString());
            games.write("\n");
        }
        games.close();

        for (Level item : levelService.getLevels()) {
            level.write(item.toString());
            level.write("\n");
        }
        level.close();
    }
}
