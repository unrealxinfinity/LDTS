package pt.up.fe.edu.dozer.model.game.arena;

import pt.up.fe.edu.dozer.model.game.elements.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoaderArenaBuilder extends ArenaBuilder{
    private final int level;
    private final List<String> lines;

    public LoaderArenaBuilder(int level, LevelReader reader) throws IOException {
        this.level = level;

        lines = reader.readLevel(level);
    }

    @Override
    protected int getWidth() {
        int width = 0;
        for (String line : lines)
            width = Math.max(width, line.length());
        return width;
    }

    @Override
    protected int getHeight() {
        return lines.size();
    }

    @Override
    protected List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == '#') walls.add(new Wall(x, y));
        }
        return walls;
    }

    @Override
    protected List<Boulder> createBoulders() {
        List<Boulder> boulders = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == '$') boulders.add(new Boulder(x, y));
        }
        return boulders;
    }

    @Override
    protected Dozer createDozer() {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == '*') return new Dozer(x, y);
        }
        return null;
    }

    @Override
    protected List<Wall> createCollisionWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'W') walls.add(new ImportantWall(x, y));
        }
        return walls;
    }

    @Override
    protected List<Target> createTargets() {
        List<Target> targets = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == '+') targets.add(new Target(x, y));
        }
        return targets;
    }

    @Override
    protected int getLevelNum() {
        return level;
    }
}
