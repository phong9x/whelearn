$(document).ready(function() {
  /* --------------------------------------------------------------------- */
  /* Metronic
  /* --------------------------------------------------------------------- */
  (function($) {
    if ($("body.login-page").length) return;

    Metronic.init(); // init metronic core components
    Layout.init(); // init current layout
    QuickSidebar.init(); // init quick sidebar
    Demo.init(); // init demo features
  })(jQuery);



  /* --------------------------------------------------------------------- */
  /* data-ba-placeholder
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$("[data-ba-placeholder]").length) return;

    $("[data-ba-placeholder]").each(function() {
      var placeholderContent = $(this).attr("data-ba-placeholder");
      $(this).attr("placeholder", placeholderContent);

      $(this).on("focus", function() {
        $(this).attr("placeholder", "");
      });

      $(this).on("blur", function() {
        $(this).attr("placeholder", placeholderContent);
      });
    });
  })(jQuery);



  /* --------------------------------------------------------------------- */
  /* .date-picker
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$(".date-picker").length) return;

    $('.date-picker').datepicker({
      rtl: Metronic.isRTL(),
      orientation: "left",
      autoclose: true,
      language: 'kr'
    });
  })(jQuery);



  /* --------------------------------------------------------------------- */
  /* #touchspin-demo
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$(".touchspin-demo").length) return;

    $(".touchspin-demo").TouchSpin({
      buttondown_class: 'btn green',
      buttonup_class: 'btn green',
      min: 1,
      stepinterval: 100,
      maxboostedstep: 1
    });
  })(jQuery);



  /* --------------------------------------------------------------------- */
  /* .ckeditor
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$(".ckeditor").length) return;

    $('.ckeditor').ckeditor({
      language: "ko"
    });
  })(jQuery);



  /* --------------------------------------------------------------------- */
  /* .control-select-all
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$(".control-select-all").length) return;

    $(document.body).on("click", ".select-all", function() {
      var wrapper = $(this).closest(".control-select-all");
      wrapper.find(".select-item").prop('checked', true);

      $.uniform.update();
    });

    $(document.body).on("click", ".deselect-all", function() {
      var wrapper = $(this).closest(".control-select-all");
      wrapper.find(".select-item").prop('checked', false);

      $.uniform.update();
    });
  })(jQuery);



  /* --------------------------------------------------------------------- */
  /* .toggle-select-all
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$(".toggle-select-all").length) return;

    $(document.body).on("change", ".select-all", function() {
      var selectAll = $(this);
      var wrapper = $(this).closest(".toggle-select-all");
      var item = wrapper.find(".select-item");

      selectAll.prop('checked') == true ? item.prop('checked', true) : item.prop('checked', false)

      $.uniform.update();
    });

    $(document.body).on("change", ".select-item", function() {
      var item = $(this);
      var wrapper = $(this).closest(".toggle-select-all");
      var selectAll = wrapper.find(".select-all");
      var countItem = wrapper.find(".select-item:not(':checked')").length;

      countItem === 0 ? selectAll.prop('checked', true) : selectAll.prop('checked', false)

      $.uniform.update();
    });
  })(jQuery);
  
  
  
  /* --------------------------------------------------------------------- */
  /* fullCalendar
  /* --------------------------------------------------------------------- */
  (function($) {
    // if (!$("#calendar").length) return;

    if (!jQuery().fullCalendar) return;

    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();

    var h = {};

    if (Metronic.isRTL()) {
      if ($('#calendar').parents(".portlet").width() <= 720) {
        $('#calendar').addClass("mobile");
        h = {
          right: 'title, prev, next',
          center: '',
          left: 'agendaDay, agendaWeek, month, today'
        };
      } else {
        $('#calendar').removeClass("mobile");
        h = {
          right: 'title',
          center: '',
          left: 'agendaDay, agendaWeek, month, today, prev,next'
        };
      }
    } else {
      if ($('#calendar').parents(".portlet").width() <= 720) {
        $('#calendar').addClass("mobile");
        h = {
          left: 'title, prev, next',
          center: '',
          right: 'today,month,agendaWeek,agendaDay'
        };
      } else {
        $('#calendar').removeClass("mobile");
        h = {
          left: 'title',
          center: '',
          right: 'prev,next,today,month,agendaWeek,agendaDay'
        };
      }
    }

    var initDrag = function(el) {
      // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
      // it doesn't need to have a start or end
      var eventObject = {
        title: $.trim(el.text()) // use the element's text as the event title
      };
      // store the Event Object in the DOM element so we can get to it later
      el.data('eventObject', eventObject);
      // make the event draggable using jQuery UI
      el.draggable({
        zIndex: 999,
        revert: true, // will cause the event to go back to its
        revertDuration: 0 //  original position after the drag
      });
    };

    var addEvent = function(title) {
      title = title.length === 0 ? "Untitled Event" : title;
      var html = $('<div class="external-event label label-default">' + title + '</div>');
      jQuery('#event_box').append(html);
      initDrag(html);
    };

    $('#external-events div.external-event').each(function() {
      initDrag($(this));
    });

    $('#event_add').unbind('click').click(function() {
      var title = $('#event_title').val();
      addEvent(title);
    });

    //predefined events
    $('#event_box').html("");
    addEvent("My Event 1");
    addEvent("My Event 2");
    addEvent("My Event 3");
    addEvent("My Event 4");
    addEvent("My Event 5");
    addEvent("My Event 6");

    $('#calendar').fullCalendar('destroy'); // destroy the calendar
    $('#calendar').fullCalendar({ //re-initialize the calendar
      lang: 'en', // Customize the language for the calendar.
      header: h,
      defaultView: 'month', // change default view with available options from http://arshaw.com/fullcalendar/docs/views/Available_Views/ 
      slotMinutes: 15,
      editable: true,
      droppable: true, // this allows things to be dropped onto the calendar !!!
      drop: function(date, allDay) { // this function is called when something is dropped

        // retrieve the dropped element's stored Event Object
        var originalEventObject = $(this).data('eventObject');
        // we need to copy it, so that multiple events don't have a reference to the same object
        var copiedEventObject = $.extend({}, originalEventObject);

        // assign it the date that was reported
        copiedEventObject.start = date;
        copiedEventObject.allDay = allDay;
        copiedEventObject.className = $(this).attr("data-class");

        // render the event on the calendar
        // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
        $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

        // is the "remove after drop" checkbox checked?
        if ($('#drop-remove').is(':checked')) {
          // if so, remove the element from the "Draggable Events" list
          $(this).remove();
        }
      },
      events: [{
        title: 'All Day Event',
        start: new Date(y, m, 1),
        backgroundColor: Metronic.getBrandColor('yellow')
      }, {
        title: 'Long Event',
        start: new Date(y, m, d - 5),
        end: new Date(y, m, d - 2),
        backgroundColor: Metronic.getBrandColor('green')
      }, {
        title: 'Repeating Event',
        start: new Date(y, m, d - 3, 16, 0),
        allDay: false,
        backgroundColor: Metronic.getBrandColor('red')
      }, {
        title: 'Repeating Event',
        start: new Date(y, m, d + 4, 16, 0),
        allDay: false,
        backgroundColor: Metronic.getBrandColor('green')
      }, {
        title: 'Meeting',
        start: new Date(y, m, d, 10, 30),
        allDay: false,
      }, {
        title: 'Lunch',
        start: new Date(y, m, d, 12, 0),
        end: new Date(y, m, d, 14, 0),
        backgroundColor: Metronic.getBrandColor('grey'),
        allDay: false,
      }, {
        title: 'Birthday Party',
        start: new Date(y, m, d + 1, 19, 0),
        end: new Date(y, m, d + 1, 22, 30),
        backgroundColor: Metronic.getBrandColor('purple'),
        allDay: false,
      }, {
        title: 'Click for Google',
        start: new Date(y, m, 28),
        end: new Date(y, m, 29),
        backgroundColor: Metronic.getBrandColor('yellow'),
        url: 'http://google.com/',
      }]
    });
  })(jQuery);



  /* --------------------------------------------------------------------- */
  /* validation form_sample_3
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$(".form_sample_3").length) return;

    var form3 = $('.form_sample_3');
    var error3 = $('.alert-danger', form3);
    var success3 = $('.alert-success', form3);

    //IMPORTANT: update CKEDITOR textarea with actual content before submit
    form3.on('submit', function() {
      for (var instanceName in CKEDITOR.instances) {
        CKEDITOR.instances[instanceName].updateElement();
      }
    });

    form3.validate({
      errorElement: 'span', //default input error message container
      errorClass: 'help-block help-block-error', // default input error message class
      focusInvalid: false, // do not focus the last invalid input
      ignore: "", // validate all fields including form hidden input
      rules: {
        nameABC: {
          minlength: 2,
          required: true
        },
        email: {
          required: true,
          email: true
        },
        options1: {
          required: true
        },
        options2: {
          required: true
        },
        select2tags: {
          required: true
        },
        datepicker: {
          required: true
        },
        occupation: {
          minlength: 5,
        },
        membership: {
          required: true
        },
        service: {
          required: true,
          minlength: 2
        },
        editor2: {
          required: true
        }
      },

      messages: { // custom messages for radio buttons and checkboxes
        membership: {
          required: "Please select a Membership type"
        },
        service: {
          required: "Please select  at least 2 types of Service",
          minlength: jQuery.validator.format("Please select  at least {0} types of Service")
        }
      },

      errorPlacement: function(error, element) { // render error placement for each input type
        if (element.parent(".input-group").size() > 0) {
          error.insertAfter(element.parent(".input-group"));
        } else if (element.attr("data-error-container")) {
          error.appendTo(element.attr("data-error-container"));
        } else if (element.parents('.radio-list').size() > 0) {
          error.appendTo(element.parents('.radio-list').attr("data-error-container"));
        } else if (element.parents('.radio-inline').size() > 0) {
          error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
        } else if (element.parents('.checkbox-list').size() > 0) {
          error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
        } else if (element.parents('.checkbox-inline').size() > 0) {
          error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
        } else {
          error.insertAfter(element); // for other inputs, just perform default behavior
        }
      },

      invalidHandler: function(event, validator) { //display error alert on form submit   
        success3.hide();
        error3.show();
        Metronic.scrollTo(error3, -200);
      },

      highlight: function(element) { // hightlight error inputs
        $(element)
          .closest('.form-group').addClass('has-error'); // set error class to the control group
      },

      unhighlight: function(element) { // revert the change done by hightlight
        $(element)
          .closest('.form-group').removeClass('has-error'); // set error class to the control group
      },

      success: function(label) {
        label
          .closest('.form-group').removeClass('has-error'); // set success class to the control group
      },

      submitHandler: function(form) {
        success3.show();
        error3.hide();
        form[0].submit(); // submit the form
      }
    });

    //apply validation on select2 dropdown value change, this only needed for chosen dropdown integration.
    $('.select2me', form3).change(function() {
      form3.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input
    });

    // initialize select2 tags
    $("#select2_tags").change(function() {
      form3.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input 
    }).select2({
      tags: ["red", "green", "blue", "yellow", "pink"]
    });

    $('.date-picker .form-control').change(function() {
      form3.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input 
    });
  })(jQuery);




});

(function($) {
	  var current = null;

	  var nc = window.tramsNC = window.tramsNC || {};

	  nc.confirm = function() {
	    current.data("confirmed", true);
	    current.trigger("click");
	  };

	  $(document).on('click', '.trams-need-confirm', function(e) {
	    var self = $(this);
	    var confirmed = self.data("confirmed");

	    if (confirmed) return true;

	    var popupId = self.attr("data-trams-confirm-popup");
	    current = self;

	    $(popupId).modal("show");

	    return false;
	  });
	})(jQuery);
