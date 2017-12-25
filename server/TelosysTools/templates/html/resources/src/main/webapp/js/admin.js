! function($) {
  $(document).ready(function() {
    (function() {
      if ($('.product-blanket-form').length) {
        $('.product-blanket-form .select-all-item').on("click", function() {
          $('.product-blanket-form .select-a-item').prop('checked', this.checked);
        });
      }
    })();



    (function() {
      if ($('.product-list').length) {
        $('.product-list .select-all-product').on("click", function() {
          $('.product-list .select-a-product').prop('checked', this.checked);
        });
      }
    })();



    (function() {
      if ($('.product-category').length) {
        var insertOption = function(select, optionArray) {
          for (var i = 0; i < optionArray.length; i++) {
            select.append('<option>' + optionArray[i] + '</option>');
          }
        };

        var select1 = $('.cat-option-1 select');
        var select2 = $('.cat-option-2 select');
        var select3 = $('.cat-option-3 select');

        var option1A = ['Blanket', 'Blanket Set'];
        var option2A = ['Print', 'Embossed'];
        var option3A = ['1 PLY', '2 PLY', 'BABY'];
        var option3B = ['1 PLY', '2 PLY'];
        var option3C = ['BCV 4 PCS', 'BCV 3 PCS', 'BCS 3 PCS'];

        insertOption(select1, option1A);
        insertOption(select2, option2A);
        insertOption(select3, option3A);

        select1.on("change", function() {
          var indexCat1 = select1.find('option:selected').index();
          var indexCat2 = select2.find('option:selected').index();
          select3.empty();

          if(indexCat1 === 0) {
            if(indexCat2 === 0) {
              insertOption(select3, option3A);
            }
            else {
              insertOption(select3, option3B);
            }
          }
          else {
            insertOption(select3, option3C);
          }
        });
        
        select2.on("change", function() {
          var indexCat1 = select1.find('option:selected').index();
          var indexCat2 = select2.find('option:selected').index();
          select3.empty();

          if(indexCat2 === 0) {
            if(indexCat1 === 0) {
              insertOption(select3, option3A);
            }
            else {
              insertOption(select3, option3C);
            }
          }
          else {
            if(indexCat1 === 0) {
              insertOption(select3, option3B);
            }
            else {
              insertOption(select3, option3C);
            }
          }
        });
      }
    })();

  });
}(jQuery);
