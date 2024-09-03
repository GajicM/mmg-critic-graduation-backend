package raf.diplomski.mmgcritic.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import raf.diplomski.mmgcritic.data.entities.games.Game;
import raf.diplomski.mmgcritic.data.entities.movies.Movie;
import raf.diplomski.mmgcritic.data.entities.music.Artist;
import raf.diplomski.mmgcritic.data.entities.music.Music;
import raf.diplomski.mmgcritic.data.entities.user.Role;
import raf.diplomski.mmgcritic.data.entities.user.User;
import raf.diplomski.mmgcritic.repositories.GameRepository;
import raf.diplomski.mmgcritic.repositories.*;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData  implements CommandLineRunner {
    private final GameRepository gameRepository;
    private final ResourceLoader resourceLoader;
    private final CSVProcessor moviesCsvLoader;
    private final MusicRepository musicRepository;
    private final ArtistRepository artistRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count()==0){
            User u=new User();
            u.setRole(Role.ADMIN);
            u.setUsername("admin");
            u.setEmail("admin@admin.com");
            u.setPassword(passwordEncoder.encode("password"));
            u.setFirstName("Milos");
            u.setLastName("Gajic");
            userRepository.save(u);
        }

        if(artistRepository.count()==0){
            List<Artist> music=moviesCsvLoader.loadArtists();
            if(music!=null){
                artistRepository.saveAll(music);
                System.out.println("artists dooone");
            }
        }

        if(musicRepository.count()==0){
            List<Music> music=moviesCsvLoader.loadAlbums();
            if(music!=null){
                musicRepository.saveAll(music);
                System.out.println("music dooone");
            }
        }
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
