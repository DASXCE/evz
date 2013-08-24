$('input[name=radiogroup]:radio').click(
		function() {
			if ($(this).val() == 'admin') {
				$('html,body').animate({
					scrollTop : $('body').offset().top
				}, 2000);
				$('#v-podaci').fadeOut(500);
				$('#vet-podaci').fadeOut(500);
				$("#save").fadeIn(400).fadeOut(400).fadeIn(400).fadeOut(400)
						.fadeIn(400).fadeOut(400).fadeIn(400);
			} else if ($(this).val() == 'owner') {
				$('#vet-podaci').fadeOut();
				$('#v-podaci').css('opacity', 0).slideDown('slow').animate({
					opacity : 1
				}, {
					queue : false,
					duration : 'slow'
				});
				$('html,body').animate({
					scrollTop : $('#v-podaci').offset().top
				}, 2600);
			} else if ($(this).val() == 'vet') {
				$('#v-podaci').fadeOut();
				$('#vet-podaci').css('opacity', 0).slideDown('slow').animate({
					opacity : 1
				}, {
					queue : false,
					duration : 'slow'
				});
				$('html,body').animate({
					scrollTop : $('#vet-podaci').offset().top
				}, 2600);
			}
		});