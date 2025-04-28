package service;

import dao.ReviewDAO;

public class ReviewService {
	private ReviewDAO reviewDAO = new ReviewDAO();

    public boolean leaveReview(int customerId, int orderId, int rating, String comments) {
        return reviewDAO.leaveReview(customerId, orderId, rating, comments);
    }
}
