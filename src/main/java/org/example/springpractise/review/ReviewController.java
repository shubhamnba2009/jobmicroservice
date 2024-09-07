package org.example.springpractise.review;

import jakarta.persistence.GeneratedValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
	ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> getReviews(@PathVariable Long companyId) {
		return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
	}

	@PostMapping("/reviews")
	public ResponseEntity<String> addReview(@PathVariable Long companyId,
											@RequestBody Review review) {
		boolean addFlag = reviewService.addReview(companyId, review);
		if(addFlag)
			return new ResponseEntity<>("Review added", HttpStatus.OK);
		return new ResponseEntity<>("Company does not exists", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<Review> getReview(@PathVariable Long companyId,
											@PathVariable Long reviewId) {
		Review review = reviewService.getReview(companyId, reviewId);
		if(review != null)
			return new ResponseEntity<>(review, HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<String> updateReview(@PathVariable Long companyId,
											   @PathVariable Long reviewId,
											   @RequestBody Review review) {
		boolean isReviewUpdated = reviewService.updateReview(companyId, reviewId, review);
		if(isReviewUpdated)
			return new ResponseEntity<>("Review updated Successfully", HttpStatus.OK);
		return new ResponseEntity<>("Company does not exists", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
											   @PathVariable Long reviewId) {
		boolean isReviewDeleted = reviewService.deleteReview(companyId, reviewId);
		if(isReviewDeleted){
			return new ResponseEntity<>("Review deleted Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Company does not exists", HttpStatus.NOT_FOUND);
	}

}
