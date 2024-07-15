package raf.diplomski.mmgcritic.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import raf.diplomski.mmgcritic.data.entities.games.Game;
import raf.diplomski.mmgcritic.data.entities.games.GameGenre;
import raf.diplomski.mmgcritic.repositories.GameRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData  implements CommandLineRunner {
    private final GameRepository gameRepository;
    @Override
    public void run(String... args) throws Exception {
        Game g=new Game();
        g.setDescription("desc");
        g.setFinalGrade(10.0);
        g.setPlatforms(List.of("PC"));
        g.setGameGenres(List.of(GameGenre.PUZZLE));
        g.setName("BG3");
        g.setDeveloper("IDKF");
        g.setReleaseDate(180000000L);
        g.setReviewList(List.of());
        gameRepository.save(g);
    }
}
