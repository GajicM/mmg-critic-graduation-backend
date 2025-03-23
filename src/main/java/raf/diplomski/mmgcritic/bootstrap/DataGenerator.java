package raf.diplomski.mmgcritic.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import raf.diplomski.mmgcritic.data.entities.Review;
import raf.diplomski.mmgcritic.data.entities.user.Role;
import raf.diplomski.mmgcritic.data.entities.user.User;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@RequiredArgsConstructor
@Component
public class DataGenerator {

    private final PasswordEncoder encoder;

    public List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>();
        Random random = new Random();

        // Sample first and last names
        String[] firstNames = {"John", "Jane", "Alice", "Bob", "Charlie", "Eve", "Grace", "Mia", "Liam", "Sophia", "Olivia", "Lucas", "Noah", "Emma", "Ava", "Isabella"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin"};

        for (int i = 0; i < count; i++) {
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];

            String username = firstName.toLowerCase() + lastName.toLowerCase() + i;
            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + i + "@example.com";

            String phoneNumber = String.format("+1234567%04d", random.nextInt(10000));
            String password = encoder.encode("password"); // Sample password
            User u=new User();
            u.setUsername(username);
            u.setPassword(password);
            u.setEmail(email);
            u.setPhoneNumber(phoneNumber);
            u.setFirstName(firstName);
            u.setLastName(lastName);
            u.setReviews(new ArrayList<>());
            if(random.nextInt(2)%2==0)
                u.setRole(Role.USER);
            else
                u.setRole(Role.CRITIC);
            users.add(u);

        }

        return users;
    }


    public  List<Review> generateReviews(int count) {
        Random random = new Random();

        // Sample comments for reviews
        ReviewData[] reviewsData = {
                new ReviewData("Absolutely loved it! Kept me engaged from start to finish.", 9),
                new ReviewData("Not bad, but there were some parts that felt a bit slow.", 6),
                new ReviewData("The visuals were stunning, and the sound design was top-notch.", 8),
                new ReviewData("Good, but I expected a bit more depth.", 7),
                new ReviewData("A solid experience, but not something I'd revisit often.", 6),
                new ReviewData("Amazing! One of the best I've experienced in a long time.", 10),
                new ReviewData("Interesting concept, but the execution could've been better.", 5),
                new ReviewData("The storyline was compelling, though it had a few predictable moments.", 7),
                new ReviewData("Had some great moments, but also a few letdowns.", 6),
                new ReviewData("A fun and enjoyable experience overall!", 8),
                new ReviewData("Somewhat overhyped, but still worth checking out.", 7),
                new ReviewData("Unique and refreshing - definitely stands out from the crowd.", 8),
                new ReviewData("A bit too lengthy, but otherwise enjoyable.", 6),
                new ReviewData("The ending was a bit disappointing, but everything else was great.", 7),
                new ReviewData("Fantastic soundtrack and visuals!", 9),
                new ReviewData("Perfect for fans of the genre; might not appeal to everyone.", 8),
                new ReviewData("Well-crafted, with impressive attention to detail.", 9),
                new ReviewData("Could’ve been better, but still decent overall.", 6),
                new ReviewData("Exceeded my expectations - would recommend to others!", 9),
                new ReviewData("A few flaws, but nothing too distracting.", 7),
                new ReviewData("Innovative and creative - a fresh take on the genre.", 8),
                new ReviewData("Great pacing and well worth the time!", 9),
                new ReviewData("Highly entertaining and definitely worth a try.", 8),
                new ReviewData("Just okay - didn’t leave a lasting impression.", 5),
                new ReviewData("Top-notch quality and very engaging!", 10)
        };
        List<Review> reviews=new ArrayList<>();
        for(int i=0;i<count;i++) {
            // Randomly select a comment and grade
            ReviewData rd = reviewsData[random.nextInt(reviewsData.length)];
            String comment=rd.comment;
            int grade = rd.rating;

            // Generate a random date in epoch time for this year
            long startOfYear = LocalDateTime.of(2023, 1, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);
            long endOfYear = LocalDateTime.of(2023, 12, 31, 23, 59).toEpochSecond(ZoneOffset.UTC);
            long datePublished = startOfYear + (Math.abs(random.nextLong()) % (endOfYear - startOfYear));
            Review r = new Review();
            r.setComment(comment);
            r.setDatePublished(datePublished);
            r.setGrade(grade);
            r.setReviewInteractionList(List.of());
            reviews.add(r);
        }
        return reviews;
    }
    private static class ReviewData {
        String comment;
        int rating;

        public ReviewData(String comment, int rating) {
            this.comment = comment;
            this.rating = rating;
        }
    }








}
