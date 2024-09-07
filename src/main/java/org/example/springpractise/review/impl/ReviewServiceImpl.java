package org.example.springpractise.review.impl;

import org.example.springpractise.company.Company;
import org.example.springpractise.company.CompanyService;
import org.example.springpractise.review.Review;
import org.example.springpractise.review.ReviewRepository;
import org.example.springpractise.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

	private final ReviewRepository reviewRepository;
	private CompanyService companyService;

	public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
		this.reviewRepository = reviewRepository;
		this.companyService = companyService;
	}

	@Override
	public List<Review> getAllReviews(Long companyId) {
		return reviewRepository.findByCompanyId(companyId);
	}

	@Override
	public boolean addReview(Long companyId, Review review) {
		Company company = companyService.getCompanyById(companyId);
		if(company != null) {
			review.setCompany(company);
			reviewRepository.save(review);
			return true;
		}
		return false;
	}

	@Override
	public Review getReview(Long companyId, Long reviewId) {
		List<Review> reviews = getAllReviews(companyId);
		return reviews.stream()
				.filter(review -> review.getId().equals(reviewId))
				.findFirst()
				.orElse(null);
	}

	@Override
	public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
		Company company = companyService.getCompanyById(companyId);
		if (company == null) {
			return false; // Company not found
		}

		// Fetch the existing review first
		Optional<Review> existingReviewOpt = reviewRepository.findById(reviewId);
		if (existingReviewOpt.isPresent()) {
			Review existingReview = existingReviewOpt.get();

			// Update only the fields that need to be changed
			existingReview.setRating(updatedReview.getRating()); // Example field
			existingReview.setDescription(updatedReview.getDescription()); // Another example field
			existingReview.setTitle(updatedReview.getTitle());
			// Update the company association
			existingReview.setCompany(company);

			// Save the updated review back to the repository
			reviewRepository.save(existingReview);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteReview(Long companyId, Long reviewId) {
		Company company = companyService.getCompanyById(companyId);
		if (company == null && !reviewRepository.existsById(reviewId)) {
			return false; // Company not found
		}
		Optional<Review> existingReviewOpt = reviewRepository.findById(reviewId);
		if (existingReviewOpt.isPresent()) {
			reviewRepository.deleteById(reviewId);
			return true;
		}
		return false;
	}
}
