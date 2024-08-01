package raf.diplomski.mmgcritic.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import raf.diplomski.mmgcritic.data.entities.games.Game;
import raf.diplomski.mmgcritic.data.entities.movies.Movie;
import raf.diplomski.mmgcritic.repositories.GameRepository;
import raf.diplomski.mmgcritic.repositories.MovieRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData  implements CommandLineRunner {
    private final GameRepository gameRepository;
    private final ResourceLoader resourceLoader;
    private final CSVProcessor moviesCsvLoader;
    private final MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {
        if(movieRepository.count()==0){
            List<Movie> movies=moviesCsvLoader.loadMovies();
            if(movies!=null){
                movieRepository.saveAll(movies);
                System.out.println("lmao done");
            }
        }
        System.out.println("movies done");
        if(gameRepository.count()==0){
            List<Game> movies=moviesCsvLoader.loadGames();
            if(movies!=null){
                gameRepository.saveAll(movies);
                System.out.println("lmao done");
            }
        }

    }
}
