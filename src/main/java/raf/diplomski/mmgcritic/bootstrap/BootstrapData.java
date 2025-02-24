package raf.diplomski.mmgcritic.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import raf.diplomski.mmgcritic.data.entities.Review;
import raf.diplomski.mmgcritic.data.entities.games.Game;
import raf.diplomski.mmgcritic.data.entities.movies.Movie;
import raf.diplomski.mmgcritic.data.entities.music.Artist;
import raf.diplomski.mmgcritic.data.entities.music.Music;
import raf.diplomski.mmgcritic.data.entities.user.Role;
import raf.diplomski.mmgcritic.data.entities.user.User;
import raf.diplomski.mmgcritic.repositories.GameRepository;
import raf.diplomski.mmgcritic.repositories.*;

import java.util.ArrayList;
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
    private final ItemRepository itemRepository;
    private final DataGenerator dataGenerator;
    private final ReviewRepository reviewRepository;

    @Override
    public void run(String... args) throws Exception {
        List<User> users;
        List<Music> music;
        List<Movie> movies;
        List<Game> games;
        if(userRepository.count()==0){
            users=dataGenerator.generateUsers(50);
            User u=new User();
            u.setRole(Role.ADMIN);
            u.setUsername("admin");
            u.setEmail("admin@admin.com");
            u.setPassword(passwordEncoder.encode("password"));
            u.setFirstName("Milos");
            u.setLastName("Gajic");
            users.add(u);
            userRepository.saveAll(users);

            System.out.println("users done");
        }

        if(artistRepository.count()==0){
            List<Artist> artists=moviesCsvLoader.loadArtists();
            if(artists!=null){
                artistRepository.saveAll(artists);
                System.out.println("artists dooone");
            }
        }

        if(musicRepository.count()==0){
           music=moviesCsvLoader.loadAlbums();
            if(music!=null){
              music=  musicRepository.saveAll(music);
                System.out.println("music dooone");
            }
        }
        if(movieRepository.count()==0){
           movies=moviesCsvLoader.loadMovies();
            if(movies!=null){
               movies= movieRepository.saveAll(movies);
                System.out.println("movies done");
            }
        }

        if(gameRepository.count()==0){
           games=moviesCsvLoader.loadGames();
            if(games!=null){
               games= gameRepository.saveAll(games);
                System.out.println("games done");
            }
        }






    }
}
