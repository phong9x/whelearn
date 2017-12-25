var namhwaPopup = {};

jQuery(document).ready(function() {
	(function($) {
		var popup       = $(".product-popup");
		var navup       = popup.find(".product-popup-image-thumbail-navup");
		var navdown     = popup.find(".product-popup-image-thumbail-navdown");
		var slider      = popup.find(".product-popup-image-thumbail-slider");
		
		var thumbItems  = popup.find(".product-popup-image-thumbail-slider-item");
		
		var aniInClass  = "product-popup-ani-in";
		var aniOutClass = "product-popup-ani-out";
		var inviClass   = "product-popup-invi";

		var createThumbs = function() {
			thumbItems.each(function(index) {
				var block = $(this);
				var img = block.find(">img");
				var url = img[0].src;

				block.css("background-image", "url('" + url + "')");
				// block.hide();
			});
		}

		var show = function() {
			popup.addClass(aniInClass);
			popup.removeClass(aniOutClass);
			popup.removeClass(inviClass);

			createThumbs();
		};

		var hide = function() {
			popup.addClass(aniOutClass);
			popup.removeClass(aniInClass);
		};

		navdown.click(function() {
			slider.animate({ scrollTop: slider.scrollTop() + 50 }, 300);
		});

		navup.click(function() {
			slider.animate({ scrollTop: slider.scrollTop() - 50 }, 300);
		});

		popup.find(".product-popup-topbar-close-btn,"
			+ ".product-popup-footer-close").click(function() {
			hide();
		});

		namhwaPopup.show = function(productID) {
			show();
		}
		namhwaPopup.hide = hide;

	})(jQuery);
});