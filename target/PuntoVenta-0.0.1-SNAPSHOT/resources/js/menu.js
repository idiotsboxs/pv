/*  jQuery ready function. Specify a function to execute when the DOM is fully loaded.  */
jQuery(document).ready(
  /* This is the function that will get executed after the DOM is fully loaded */
  function () {
    /* Next part of code handles hovering effect and submenu appearing */
	  jQuery('.nav li').hover(
      function () { //appearing on hover
    	  jQuery('ul', this).fadeIn();
      },
      function () { //disappearing on hover
    	  jQuery('ul', this).fadeOut();
      }
    );
  }
);