package model;

//Review class for storing customer reviews
public class Review {
 private int reviewId;
 private String comments;
 private int rating;
 
 public Review(int reviewId, String comments, int rating) {
     this.reviewId = reviewId;
     this.comments = comments;
     this.rating = rating;
 }
 
 public int getReviewId() {
     return reviewId;
 }

 public String getComments() {
     return comments;
 }

 public int getRating() {
     return rating;
 }
}
