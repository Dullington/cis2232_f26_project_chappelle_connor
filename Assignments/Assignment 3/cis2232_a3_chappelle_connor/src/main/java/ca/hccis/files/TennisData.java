package ca.hccis.files;

import ca.hccis.files.entity.Tennis;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TennisData {

    private static final String PATH_NAME = "c:\\cis2232\\players.json";

    private static final Map<Integer, Tennis> playerMap = new HashMap<>();
    private static final Gson gson = new Gson();

    private static int nextId = 1;

    public static synchronized void initialize() {
        Path directory = Paths.get("c:\\cis2232");
        Path file = Paths.get(PATH_NAME);

        try {
            Files.createDirectories(directory);

            if (Files.exists(file)) {
                List<String> lines = Files.readAllLines(file);
                List<Tennis> loadedPlayers = new ArrayList<>();
                int maxId = 0;

                for (String line : lines) {
                    if (!line.trim().isEmpty()) {
                        Tennis player = gson.fromJson(line, Tennis.class);
                        loadedPlayers.add(player);

                        if (player.getId() > maxId) {
                            maxId = player.getId();
                        }
                    }
                }

                nextId = maxId + 1;

                playerMap.clear();
                Set<Integer> usedIds = new HashSet<>();

                for (Tennis player : loadedPlayers) {
                    int id = player.getId();

                    if (id <= 0 || usedIds.contains(id)) {
                        id = nextId++;
                        player.setId(id);
                    }

                    usedIds.add(id);
                    playerMap.put(id, player);
                }
                
                saveAll();

            } else {
                Files.createFile(file);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized int getNextId() {
        return nextId++;
    }

    public static synchronized void addPlayer(Tennis player) {
        playerMap.put(player.getId(), player);
        saveAll();
    }

    public static synchronized Map<Integer, Tennis> getPlayers() {
        return new HashMap<>(playerMap);
    }

    public static synchronized int getPlayerCount() {
        return playerMap.size();
    }

    private static synchronized void saveAll() {
        Path file = Paths.get(PATH_NAME);

        try {
            StringBuilder content = new StringBuilder();

            for (Tennis player : playerMap.values()) {
                content.append(gson.toJson(player))
                        .append(System.lineSeparator());
            }

            Files.writeString(file, content.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}