package raf.diplomski.mmgcritic.data.entities.movies;

public enum MovieGenre {
    ROMANCE("Romance"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    THRILLER("Thriller"),
    ACTION("Action"),
    ADVENTURE("Adventure"),
    HORROR("Horror"),
    MUSICAL("Musical"),
    FAMILY("Family"),
    ANIMATION("Animation"),
    FANTASY("Fantasy"),
    MYSTERY("Mystery"),
    SCIENCE_FICTION("Science Fiction"),
    HISTORY("History"),
    MUSIC("Music"),
    CRIME("Crime"),
    TV_MOVIE("TV Movie"),
    WAR("War"),
    WESTERN("Western"),
    DOCUMENTARY("Documentary");
    private final String value;
    MovieGenre(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
    public static MovieGenre fromString(String genre) {
        for (MovieGenre movieGenre : MovieGenre.values()) {
            if (movieGenre.getValue().equalsIgnoreCase(genre.trim())) {
                return movieGenre;
            }
        }
        throw new IllegalArgumentException("No enum constant " + MovieGenre.class.getCanonicalName() + "." + genre);
    }
}
