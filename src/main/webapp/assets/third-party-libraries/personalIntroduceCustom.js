
(function($) {
 "use strict"; // Start of use strict
 /* ---------------------------------------------- /*
	 * Preloader
	/* ---------------------------------------------- */

	$(window).on('load', function() {
        $('#loading').fadeOut();
        $('#preloader').delay(300).fadeOut('slow')
});
/*------------------------------------------------
 * select options    
------------------------------------------------*/  
$(document).ready(function () {
    $(".btn-select").each(function (e) {
        var value = $(this).find("ul li.selected").html();
        if (value != undefined) {
            $(this).find(".btn-select-input").val(value);
            $(this).find(".btn-select-value").html(value);
        }
    });
});

$(document).on('click', '.btn-select', function (e) {
    e.preventDefault();
    var ul = $(this).find("ul");
    if ($(this).hasClass("active")) {
        if (ul.find("li").is(e.target)) {
            var target = $(e.target);
            target.addClass("selected").siblings().removeClass("selected");
            var value = target.html();
            $(this).find(".btn-select-input").val(value);
            $(this).find(".btn-select-value").html(value);
        }
        ul.hide();
        $(this).removeClass("active");
    }
    else {
        $('.btn-select').not(this).each(function () {
            $(this).removeClass("active").find("ul").hide();
        });
        ul.slideDown(300);
        $(this).addClass("active");
    }
});

$(document).on('click', function (e) {
    var target = $(e.target).closest(".btn-select");
    if (!target.length) {
        $(".btn-select").removeClass("active").find("ul").hide();
    }
});
/*--------------------------
	counter
---------------------------- */	
      // start all the timers
      $('.timer').each(count);
     
      function count(options) {
        var $this = $(this);
        options = $.extend({}, options || {}, $this.data('countToOptions') || {});
        $this.countTo(options);
      }      


    // Offset for Main Navigation
    $('#mainNav').affix({
        offset: {
            top: 10
        }
    })
/*--------------------------
	Scrolling Animations
---------------------------- */	
    // Initialize WOW.js Scrolling Animations
/*    new WOW().init();*/
/*------------------------------------------------
 * owlCarousel    
------------------------------------------------*/  
$(function () {
              var owl = $(".owl-carousel");

  // Custom Navigation Events
  $(".next").on('click', function(){
    owl.trigger('owl.next');
  });
  $(".prev").on('click', function(){
    owl.trigger('owl.prev');
  });
        }); 
	
$(function () {
              var owl = $(".owl-carousel2");
 
  // Custom Navigation Events
  $(".next").on('click', function(){
    owl.trigger('owl.next');
  });
  $(".prev").on('click', function(){
    owl.trigger('owl.prev');
  });
        }); 

})(jQuery); // End of use strict


