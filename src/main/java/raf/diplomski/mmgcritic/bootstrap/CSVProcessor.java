package raf.diplomski.mmgcritic.bootstrap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import raf.diplomski.mmgcritic.data.entities.Review;
import raf.diplomski.mmgcritic.data.entities.games.Game;
import raf.diplomski.mmgcritic.data.entities.games.GameGenre;
import raf.diplomski.mmgcritic.data.entities.movies.Movie;
import raf.diplomski.mmgcritic.data.entities.movies.MovieGenre;
import raf.diplomski.mmgcritic.data.entities.music.Artist;
import raf.diplomski.mmgcritic.data.entities.music.Music;
import raf.diplomski.mmgcritic.data.entities.user.User;
import raf.diplomski.mmgcritic.repositories.ArtistRepository;
import raf.diplomski.mmgcritic.repositories.ReviewRepository;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Component
public class CSVProcessor {
    private final Resource resourceMovies;
    private final Resource resourceGames;
    private final Resource resourceAlbums;
    private final Resource resourceArtists;
    private final ArtistRepository artistRepository;
    private final ReviewRepository reviewRepository;


    public CSVProcessor(ResourceLoader resourceLoader, ArtistRepository artistRepository, ReviewRepository reviewRepository) {
        resourceMovies = resourceLoader.getResource("classpath:movies.csv");
        resourceGames= resourceLoader.getResource("classpath:Video Games Data.csv");
        resourceAlbums= resourceLoader.getResource("classpath:spotify_albums.csv");
        resourceArtists= resourceLoader.getResource("classpath:spotify_artists.csv");
        this.artistRepository=artistRepository;
        this.reviewRepository = reviewRepository;
    }
    public List<Movie> loadMovies() {
        List<Movie> movies=new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(resourceMovies.getFile()))) {
            csvReader.readNext();
            String[] record = csvReader.readNext();
            while(csvReader.getLinesRead()<1400){
                try{
                    if(Boolean.parseBoolean(record[8]))
                        continue;
                    if(record[19].isEmpty())
                        continue;
                    Movie movie = new Movie();
                    movie.setId(Long.valueOf(record[0]));
                    movie.setTitle(record[1]);
                    movie.setVoteAverage(Double.parseDouble(record[2]));
                    movie.setVoteCount(Integer.parseInt(record[3]));
                    movie.setStatus(record[4]);
                    movie.setReleaseDate(convertToEpoch(record[5]));
                    movie.setRevenue(Double.parseDouble(record[6]));
                    movie.setRuntime(Integer.parseInt(record[7]));
                    movie.setBackdropPath(record[9]);
                    movie.setBudget(Integer.parseInt(record[10]));
                    movie.setHomepage(record[11]);
                    movie.setImdbId(record[12]);
                    movie.setOriginalLanguage(record[13]);
                    movie.setOriginalTitle(record[14]);
                    movie.setOverview(record[15]);
                    movie.setPopularity(Double.parseDouble(record[16]));
                    movie.setPosterPath(record[17]);
                    movie.setTagline(record[18]);
                    movie.setGenres(Arrays.stream(record[19].split(",")).map(String::trim).map(MovieGenre::fromString).toList());
                    movie.setProductionCompanies(record[20]);
                    movie.setProductionCountries(record[21]);
                    movie.setSpokenLanguages(record[22]);
                    movie.setKeywords(record[23]);
                    if(movie.getVoteCount()==null)
                        movie.setVoteAverage(0.0);
                    movie.setReviews(new ArrayList<>());
                    movies.add(movie);
                    record=csvReader.readNext();
                }catch (Exception e){
                    e.printStackTrace();
                    System.err.println(Arrays.toString(record));
                    record= csvReader.readNext();

                }



            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        System.out.println("done");
        return movies;
    }


    public  long convertToEpoch(String dateString) throws ParseException {
        // Define the date format according to your input
       try{
           if(dateString.length()<=4){
               dateString="1/1/"+dateString;
           }
           try{
               SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
               // Set the time zone to UTC to avoid time zone issues
               sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

               // Parse the date string to a Date object
               Date date = sdf.parse(dateString);

               // Convert Date to epoch time in milliseconds
               return date.getTime();
           }catch (ParseException e){
               SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
               // Set the time zone to UTC to avoid time zone issues
               sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

               // Parse the date string to a Date object
               Date date = sdf.parse(dateString);

               // Convert Date to epoch time in milliseconds
               return date.getTime();
           }
       }catch (Exception e){
           return 1722524555;
       }


    }
    public List<Game> loadGames(){
        List<Game> games=new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(resourceGames.getFile()))) {
            csvReader.readNext();
            String[] resource = csvReader.readNext();
            while (csvReader.getLinesRead() < 64017) {
                Game game=new Game();
                game.setTitle(resource[1]);

                if(games.stream().anyMatch(game1 -> game1.getTitle().equals(game.getTitle()))){
                 Optional<Game> game2=   games.stream().filter(game1 ->game1.getTitle().equals(game.getTitle())) .findFirst();
                   game2.orElseThrow().getPlatforms().add(resource[2]);
                    resource=csvReader.readNext();
                    continue;
                }
                if(resource[3].isEmpty()) {
                    resource=csvReader.readNext();
                    continue;
                }
                game.setImageUrl(resource[0]);
                game.setPlatforms(new ArrayList<>());
                game.getPlatforms().add(resource[2]);
                game.setGameGenres(Arrays.stream(resource[3].split(",")).map(GameGenre::fromString).toList());
                game.setPublisher(resource[4]);
                game.setDeveloper(resource[5]);
                game.setReviews(new ArrayList<>());

                game.setFinalGrade(resource[6].isEmpty()?0.0:Double.parseDouble(resource[6]));
                game.setTotalSales(resource[7].isEmpty()?0.0:Double.parseDouble(resource[7]));
                game.setReleaseDate(resource[12].isEmpty()?0L:convertToEpoch(resource[12]));
                game.setVoteCount(0L);
                games.add(game);
                resource=csvReader.readNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("done game 1");
        return games;
    }

    public List<Music> loadAlbums(){
        List<Music> albums=new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(resourceAlbums.getFile()))) {
            csvReader.readNext();
            String[] resource = csvReader.readNext();
            while (csvReader.getLinesRead() < 15000) {
                Music music=new Music();
                music.setTitle(resource[8]);
                if(albums.stream().anyMatch(album1 -> album1.getTitle().equals(music.getTitle()))){
                    resource=csvReader.readNext();
                    continue;
                }
                if(artistRepository.findById(resource[2]).isPresent())
                    music.setArtist(artistRepository.findById(resource[2]).orElse(null));
                else
                {
                    resource=csvReader.readNext();
                    continue;
                }
                if(resource[7].contains("url':"))
                    music.setImageUrl(resource[7].split("url':")[1].split(",")[0].replaceAll("'",""));
                else
                    music.setImageUrl("");
                music.setReleaseDate(convertToEpoch(resource[9]));
                music.setTotalTracks(Long.valueOf(resource[11]));
                music.setReviews(new ArrayList<>());

                music.setFinalGrade(0.0);
                music.setVoteCount( 0L);

                albums.add(music);
                resource=csvReader.readNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("done music 1");
        return albums;
    }

    public List<Artist> loadArtists(){
        List<Artist> artists=new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(resourceArtists.getFile()))) {
            csvReader.readNext();
            String[] resource = csvReader.readNext();
            while (csvReader.getLinesRead() < 15000) {
              Artist a=new Artist();
              a.setPopularity(resource[1]);
              a.setId(resource[4]);
              a.setGenres(Arrays.stream(resource[3].replaceAll("]\\[","").split(",")).toList());
              a.setName(resource[5]);
              artists.add(a);
              resource=csvReader.readNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("done music 1");
        return artists;
    }





    }


