package pt.up.fe.edu.dozer.model.game.arena;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LevelReader {
    public List<String> readLevel(int level) throws IOException {
        String path = "src/main/resources/levels/level" + level + ".lvl";
        BufferedReader br = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8);

        return readLines(br);
    }

    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            lines.add(line);
        return lines;
    }
}
