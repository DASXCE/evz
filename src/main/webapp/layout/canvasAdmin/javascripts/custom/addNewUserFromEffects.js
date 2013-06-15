$('input[name=privilegija]:radio').click(
		function() {
			if ($(this).val() == 'admin') {
				$('html,body').animate({
					scrollTop : $('#nalog').offset().top
				}, 1000);
				$('#podaci').fadeOut(500);
				$("#save").fadeIn(400).fadeOut(400).fadeIn(400).fadeOut(400)
						.fadeIn(400).fadeOut(400).fadeIn(400);
			} else if ($(this).val() == 'vlasnik') {
				$('#podaci').css('opacity', 0).slideDown('slow').animate({
					opacity : 1
				}, {
					queue : false,
					duration : 'slow'
				});
				$('html,body').animate({
					scrollTop : $('#podaci').offset().top
				}, 1000);
			}
		});