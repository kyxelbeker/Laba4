document.getElementById('reviewForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const name = document.getElementById('name').value;
    const comment = document.getElementById('comment').value;
    const rating = document.getElementById('rating').value;

    const review = {
        reviewerName: name,
        comment: comment,
        rating: parseInt(rating)
    };

    fetch('/api/reviews', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(review)
    })
    .then(response => response.json())
    .then(data => {
        addReviewToPage(data);
    });

    // Очистка формы после добавления отзыва
    document.getElementById('reviewForm').reset();
});

function addReviewToPage(review) {
    const reviewList = document.getElementById('reviewList');
    const reviewDiv = document.createElement('div');
    reviewDiv.classList.add('review');

    reviewDiv.innerHTML = `
        <h3>${review.reviewerName}</h3>
        <p>Оценка: ${review.rating}/5</p>
        <p>${review.comment}</p>
    `;

    reviewList.appendChild(reviewDiv);
}

window.onload = function() {
    fetch('/api/reviews')
    .then(response => response.json())
    .then(data => {
        data.forEach(review => addReviewToPage(review));
    });
};
