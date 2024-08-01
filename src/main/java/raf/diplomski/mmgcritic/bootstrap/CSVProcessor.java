package raf.diplomski.mmgcritic.bootstrap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import raf.diplomski.mmgcritic.data.entities.games.Game;
import raf.diplomski.mmgcritic.data.entities.games.GameGenre;
import raf.diplomski.mmgcritic.data.entities.movies.Movie;
import raf.diplomski.mmgcritic.data.entities.movies.MovieGenre;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class CSVProcessor {
    private final Resource resourceMovies;
    private final Resource resourceGames;
    public CSVProcessor(ResourceLoader resourceLoader) {
        resourceMovies = resourceLoader.getResource("classpath:movies.csv");
        resourceGames= resourceLoader.getResource("classpath:Video Games Data.csv");
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
                    movie.setId(Integer.valueOf(record[0]));
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
                game.setReviewList(new ArrayList<>());

                game.setFinalGrade(resource[6].isEmpty()?0.0:Double.parseDouble(resource[6]));
                game.setTotalSales(resource[7].isEmpty()?0.0:Double.parseDouble(resource[7]));
                game.setReleaseDate(resource[12].isEmpty()?0L:convertToEpoch(resource[12]));
      //          System.out.println(game);
                games.add(game);
                resource=csvReader.readNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("done game 1");
        return games;
    }



    }


