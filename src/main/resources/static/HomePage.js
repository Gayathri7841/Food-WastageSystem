const carousel = document.querySelector('.carousel');
const slides = document.querySelectorAll('.carousel img');
const prevButton = document.querySelector('.carousel-btn.prev');
const nextButton = document.querySelector('.carousel-btn.next');

let currentIndex = 0;

// Function to update the carousel position
function updateCarousel() {
  const slideWidth = slides[0].clientWidth;
  carousel.style.transform = `translateX(-${currentIndex * slideWidth}px)`;
}

// Go to the next slide
nextButton.addEventListener('click', () => {
  currentIndex = (currentIndex + 1) % slides.length; // Loop to the first slide after the last
  updateCarousel();
});

// Go to the previous slide
prevButton.addEventListener('click', () => {
  currentIndex = (currentIndex - 1 + slides.length) % slides.length; // Loop to the last slide before the first
  updateCarousel();
});

// Automatically slide every 5 seconds
setInterval(() => {
  nextButton.click();
}, 2000); // Adjust time interval as needed
document.addEventListener("DOMContentLoaded", function() {
    // Ensure the animation starts fresh on page load
    const overlayText = document.querySelector('.carousel-overlay h1, .carousel-overlay p');
    overlayText.style.animation = 'none'; // Reset animation
    overlayText.offsetHeight; // Trigger a reflow
    overlayText.style.animation = ''; // Re-apply animation
});

