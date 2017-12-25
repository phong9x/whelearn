jQuery(document).ready(function() {

	(function($) {
		var nav      = $(".nav-bar");
		var gnbItems = nav.find(".nav-bar-gnb-item");
		var snbItems = nav.find(".nav-bar-snb-item");

		snbItems.each(function( index ) {
			var gnbItem = $(gnbItems[index]);
			var x = gnbItems.position().left;
			var snbItem = $(this);
			var computedWidth = gnbItem.width() + 74;

			if(index === 0) {
				snbItem.css("margin-left", x);
			}

			snbItem.css("width", computedWidth);
		});

	})(jQuery);



	(function($) {
		$(".pgb-row-block-top, .product-popup-image-big-box").each(function(index) {
			var block = $(this);
			var img = block.find(">img");
			var url = img[0].src;

			block.css("background-image", "url(" + url + ")");
		});

	})(jQuery);



	// (function($) {
	// 	$(".pgb-row-block-top").each(function(index) {
	// 		var block = $(this);
	// 		var img = block.find(">img");
	// 		var url = img[0].src;

	// 		block.css("background-image", "url(" + url + ")");
	// 	});

	// })(jQuery);



	(function($) {
		$(".NCL-notice-table-col-1 a").each(function(index) {
			var block = $(this);
			var text = block.text();
			var cap = 24;

			console.log(text);

			if(text.length > cap) {
				text = text.substring(0, cap) + "...";
				block.text(text);
			}
		});
	})(jQuery);



	(function($) {
		$(".product-category").each(function(index) {
			var block = $(this);
			var form = $(this).find("form");

			var print = block.find(".print");
			var print1ply = block.find(".print-1ply");
			var print2ply = block.find(".print-2ply");
			var printbaby = block.find(".print-baby");

			var embossed = block.find(".embossed");
			var embossed1ply = block.find(".embossed-1ply");
			var embossed2ply = block.find(".embossed-2ply");

			var printSwitch = [".print-1ply", ".print-2ply", ".print-baby"];
			var embossedSwitch = [".embossed-1ply", ".embossed-2ply"];

			var disablePrint = function() {
				print.removeClass("green");
				print1ply.removeClass("black").addClass("disable");
				print2ply.removeClass("black").addClass("disable");
				printbaby.removeClass("black").addClass("disable");
			}

			var disableEmbossed = function() {
				embossed.removeClass("orange");
				embossed1ply.removeClass("black").addClass("disable");
				embossed2ply.removeClass("black").addClass("disable");
			}

			var enablePrint = function() {
				print.addClass("green");
				print1ply.addClass("black");
				print1ply.removeClass("disable");
				print2ply.removeClass("disable");
				printbaby.removeClass("disable");

				syncCheckbox(printSwitch[0]);
			}

			var enableEmbossed = function() {
				embossed.addClass("orange");
				embossed1ply.addClass("black");
				embossed1ply.removeClass("disable");
				embossed2ply.removeClass("disable");

				syncCheckbox(embossedSwitch[0]);
			}

			var createSwitch = function(swit) {
				$.each(swit, function( index, value ) {
					$(value).click(function () {
						if($(this).hasClass("disable")) return;
						$(swit.join(",")).removeClass("black");
						$(this).addClass("black");
						syncCheckbox(value);
					});
				});
			};

			var syncCheckbox = function(clazz) {
				clazz = clazz.substring(1);
				var checkbox = form.find("[value=" + clazz + "]");
				checkbox.prop("checked", true);

				form.submit();
			}

			embossed.click(function() {
				enableEmbossed();
				disablePrint();
			});

			print.click(function() {
				enablePrint();
				disableEmbossed();
			});

			createSwitch(printSwitch);			
			createSwitch(embossedSwitch);			
		});
	})(jQuery);

});


